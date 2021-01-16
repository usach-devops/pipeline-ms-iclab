def execute() {
    def branchName = validate.getBranchName()
    println 'run maven cd'

    stage('gitDiff') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
    
    }
	stage('downloadNexus') {
		env.JENKINS_STAGE = env.STAGE_NAME
		echo env.JENKINS_STAGE
		//se descarga repositorio mavenci-repo
		sh 'curl -X GET -u admin:DevOps2020 http://192.81.214.49:8081/repository/mavenci-repo/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
    }
	
	stage('run') {
		//Ejecutar artefacto descargado.
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
		//falta el directorio local del job-nexus  {
               //sh 'java -jar DevOpsUsach2020-0.0.1.jar &'

    }
	
    stage('Test') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
        sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
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
