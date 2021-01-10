def call() {
    pipeline {
        agent any
        stages {
            stage('Pipeline') {
                steps {
                    script {
                        echo 'INICIO PIPELINE'
                        def buildtool = buildtool.get()

                        if(buildtool == BuildToolType.Maven){
                            echo 'Buld tool es MAVEN'
                        }else{
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
