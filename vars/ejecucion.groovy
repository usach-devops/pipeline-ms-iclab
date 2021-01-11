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
                                error 'Nombre de branch no cumple con las convenciones de gitflow'
                                break
                        }
                    }
                }
            }
        }
    }
}

return this
