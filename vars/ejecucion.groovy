def call() {
    pipeline {
        agent any
        stages {
            stage('Pipeline') {
                steps {
                    script {
                        echo 'INICIO PIPELINE'
                    }
                }
            }
        }
        post {
            success {
                echo 'PIPELINE OK'        }
            failure {
                echo 'PIPELINE ERROR'        }
        }
    }
}

return this
