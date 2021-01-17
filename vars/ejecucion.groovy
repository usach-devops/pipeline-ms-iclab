def call() {
    pipeline {
        agent any

        stages {
            stage('Pipeline') {
                steps {
                    script {
						def tech =  validate.getTech()
						figlet 'Tecnologia: ' + tech
						
                        def branchName = validate.getBranchName()

                        figlet 'Branch ' +validate.getValidBranchName()
                        figlet 'Tipo branch ' + branchName

                        git.setCredential()
                        
                        switch (branchName) {
                           case ['develop', 'feature']:
                                pipelineci.execute()
                                break
                           case 'release':
                                pipelinecd.execute()
                                break
                            case 'main':
                                env.ERROR_MESSAGE = 'Branch main no tiene pipeline'
                                error env.ERROR_MESSAGE
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
                script {
                    notification.success()
                }
            }
            failure {
                script {
                    echo 'env.ERROR_MESSAGE ='+env.ERROR_MESSAGE
                    //mensaje de error por defecto
                    if (env.ERROR_MESSAGE == '' || env.ERROR_MESSAGE == null) {
                        notification.failure()
                    }else {
                        //cuando se agrega un mensaje "personalizado"
                        notification.failure(env.ERROR_MESSAGE)
                    }
                }
            }
        }
    }
}
return this
