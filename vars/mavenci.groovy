def execute() {
    println 'run maven ci'

    stage('compile') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh './mvnw clean compile -e'
    }
    stage('unitTest') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh './mvnw clean test -e'
    }
    stage('jar') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh './mvnw clean package -e'
    }
    stage('sonar') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        withSonarQubeEnv(installationName: 'sonar-server') {
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        }
    }
    stage('nexusUpload') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        //imagino que las versiones de esto deben ser progresivas?
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'mavenci-repo', 
        packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/DevOpsUsach2020-0.0.1.jar']], 
        mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
    stage('gitCreateRelease') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
}
return this