def call() {
    pipeline {
        agent any
        stages {
            stage('Pipeline') {
                steps {
                    script {
                        echo 'INICIO PIPELINE'
                        def tool = buildtool.get()

                        if (tool == 'maven') {
                            echo 'Build tool es MAVEN'
                        }else {
                            echo 'Build tool es Gradle'
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
