def execute() {
    println 'run gradle ci'

    stage('build_test') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './gradlew clean build'
        }catch (Exception e){
            executeError(e)
        }
    }
    stage('sonar') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            def scannerHome = tool 'sonar-scanner';
            echo "scannerHome = ${scannerHome}"
            echo "JOB = ${env.JOB_NAME}"
            echo "RAMA = ${validate.getValidBranchName()}"
            echo "BUILD =${env.BUILD_NUMBER}"
            
            def reponame = env.GIT_URL.replaceFirst(/^.*\/([^\/]+?).git$/, '$1')
            echo "reponame =${reponame}"
            
            
            withSonarQubeEnv(installationName: 'sonar-server') {        
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${reponame}-${validate.getValidBranchName()}-${env.BUILD_NUMBER} -Dsonar.java.binaries=build"
            }
        }catch (Exception e){
            executeError(e)
        }
    }
    stage('run') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh 'bash gradlew bootRun &'
            sleep 20
        }catch (Exception e){
            executeError(e)
        }
    }
    stage('rest') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh "curl -X GET 'http://localhost:8085/rest/mscovid/test?msg=testing'"
        }catch (Exception e){
            executeError(e)
        }
    }
    stage('nexus') {
        try{
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'gradleci-repo',
                            packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar',
                            filePath: 'build/libs/DevOpsUsach2020-0.0.1.jar']],
                            mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'DevOpsUsach2020', version: '1.0.0']]]
        }catch (Exception e){
            executeError(e)
        }
    }
}

def executeError(e) {
    //error para output del pipeline mas detallado
    echo "OUTPUT ERROR ${e.toString()}"
    //Error para slack desde post actions en ejecucion.groovy
    def message = env.ERROR_MESSAGE == '' || env.ERROR_MESSAGE == null ? "[Stage ${env.JENKINS_STAGE}] Pipeline aborted due stage failure" : env.ERROR_MESSAGE 
    error message

}

return this
