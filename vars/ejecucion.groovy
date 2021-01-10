def call() {
    pipeline {
        agent any
        stages {
            stage('Pipeline') {
                steps {
                    script {
                        echo 'INICIO PIPELINE'
                        def buildtool1 = buildtool.get()

                        if (buildtool1 == 'maven') {
                            echo 'Buld tool es MAVEN'
                        }else {
                            echo 'Buld tool es Gradle'
                        }
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
