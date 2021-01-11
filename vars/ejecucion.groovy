def call() {
    pipeline {
        agent any

        stages {
            stage('Pipeline') {
                steps {
                    script {
                        def branchName = validate.getBranchName()

                        figlet env.GIT_BRANCH
                        println 'branch detectado ' + branchName

                        switch (branchName) {
                           case ['develop', 'feature']:

                                pipelineci.execute()
                                break
                           case 'release':
                                pipelinecd.execute()
                                break
                           default: 
                                env.ERROR_MESSAGE = 'Nombre de branch no cumple con las convenciones de gitflow'
                                error env.ERROR_MESSAGE
                                break
                        }
                    }
                }
            }
        }

        post {
            success {
                notification.success();
            }
            failure {
                //mensaje de error por defecto
                if (env.ERROR_MESSAGE == '') {
                    notification.failure();
                }else {
                    //cuando se agrega un mensaje "personalizado"
                    notification.failure(env.ERROR_MESSAGE);
                }
            }
        }
    }
}



return this
