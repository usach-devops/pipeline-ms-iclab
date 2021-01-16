def execute() {
    def branchName = validate.getBranchName()
    boolean allStagesPassed = true;

    println 'run maven ci'

    stage('compile') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean compile -e'
        }catch (Exception e){
            allStagesPassed = false
        }

    }
    stage('unitTest') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean test -e'
        }catch (Exception e){
            allStagesPassed = false
        }
    }
    stage('jar') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean package -e'
        }catch (Exception e){
            allStagesPassed = false
        }

    }
    stage('sonar') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            withSonarQubeEnv(installationName: 'sonar-server') {
                sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }
            // timeout(time: 15, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
            //     def qg = waitForQualityGate webhookSecretId: 'DevOps2020' // Reuse taskId previously collected by withSonarQubeEnv
            //     echo "Status: ${qg.status}"
            //     if (qg.status != 'OK') {
            //         throw new Exception("Pipeline aborted due to quality gate failure: ${qg.status}");
            //     }
            // }
        }catch (Exception e){
            allStagesPassed = false
        }
    }

      stage("Quality Gate"){
            timeout(time: 15, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
                def qg = waitForQualityGate webhookSecretId: 'devops2020' // Reuse taskId previously collected by withSonarQubeEnv
                echo "Status: ${qg.status}"
                if (qg.status != 'OK') {
                    throw new Exception("Pipeline aborted due to quality gate failure: ${qg.status}");
                }
            }
      }


    stage('nexusUpload') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            //imagino que las versiones de esto deben ser progresivas?
            //se crea en nexus repositorio mavenci-repo
            nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'mavenci-repo', 
            packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/DevOpsUsach2020-0.0.1.jar']], 
            mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        }catch (Exception e){
            allStagesPassed = false
        }
    }

    if (branchName == 'develop' && allStagesPassed) {
        stage('gitCreateRelease') {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
        }
    }

}

return this