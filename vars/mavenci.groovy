def execute() {
    println 'run maven ci'

    stage('compile') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh 'mvn clean compile -e'
    }
    stage('unitTest') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('jar') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('sonar') {
        env.JENKINS_STAGE = env.STAGE_NAME
         echo env.JENKINS_STAGE
    }
    stage('nexusUpload') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('gitCreateRelease') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
}
return this