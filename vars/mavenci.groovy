def execute() {
    def branchName = validate.getBranchName()

    figlet 'run maven ci'
    def estado = 'OK'

    stage('compile') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean compile -e'
        }catch (Exception e) {
            executeError(e)
        }
    }
    stage('unitTest') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean test -e'
        }catch (Exception e) {
            executeError(e)
        }
    }
    stage('jar') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh './mvnw clean package -e'
        }catch (Exception e) {
            executeError(e)
        }
    }
    stage('sonar') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            def scannerHome = tool 'sonar-scanner'
            echo "scannerHome = ${scannerHome}"
            echo "JOB = ${env.JOB_NAME}"
            echo "RAMA = ${validate.getValidBranchName()}"
            echo "BUILD =${env.BUILD_NUMBER}"

            def reponame = env.GIT_URL.replaceFirst(/^.*\/([^\/]+?).git$/, '$1')
            echo "reponame =${reponame}"

            withSonarQubeEnv(installationName: 'sonar-server') {
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${reponame}-${validate.getValidBranchName()}-${env.BUILD_NUMBER} -Dsonar.java.binaries=build"
            }
        }catch (Exception e) {
            executeError(e)
        }
    }

    stage('Quality Gate') {
        try {
            sleep(10)
            timeout(time: 15, unit: 'MINUTES') {
                def qg = waitForQualityGate()
                echo "Status: ${qg.status}"
                if (qg.status != 'OK') {
                    env.ERROR_MESSAGE = "Pipeline aborted due to quality gate failure: ${qg.status}"
                    error env.ERROR_MESSAGE
                }
            }
        }catch (Exception e) {
            executeError(e)
        }
    }

    stage('nexusUpload') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'mavenci-repo',
            packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/DevOpsUsach2020-0.0.1.jar']],
            mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        }catch (Exception e) {
            executeError(e)
        }
    }

    if (branchName == 'develop' && estado == 'OK') {
        stage('gitCreateRelease') {
            try {
                env.JENKINS_STAGE = env.STAGE_NAME
                echo env.JENKINS_STAGE

                def version = 'release-v' + validate.version()

                if (git.checkIfBranchExists(version)) {
                    git.deleteBranch(version)
                    git.createBranch(branchName, version)
                } else {
                    git.createBranch(branchName, version)
                }
            }catch (Exception e) {
                executeError(e)
            }
        }
    }
}

def executeError(e) {
    estado = 'error'
    //error para output del pipeline mas detallado
    echo "OUTPUT ERROR ${e.toString()}"
    //Error para slack desde post actions en ejecucion.groovy
    def message = env.ERROR_MESSAGE == '' || env.ERROR_MESSAGE == null ? "[Stage ${env.JENKINS_STAGE}] Pipeline aborted due stage failure" : env.ERROR_MESSAGE
    error message
}

return this
