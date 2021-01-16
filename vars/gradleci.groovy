def execute() {
    println 'run gradle ci'

    stage('gitDiff') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh 'git diff origin/main...origin/feature-dir-inicial'
        
    }
    stage('nexusDownload') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('run') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('test') {
        env.JENKINS_STAGE = env.STAGE_NAME
         echo env.JENKINS_STAGE
    }
    stage('gitMergeMaster') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('gitMergeDevelop') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
    stage('gitTagMaster') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    }
}
return this