package pipeline.git

def checkIfBranchExists(String branch) {
    def output = sh (script: "git ls-remote --heads origin ${branch}", returnStdout: true)
    echo output

    if (output?.trim()) {
        return true
    } else {
        return false
    }
}

def deleteBranch(String branch) {
    echo "deleteBranch"
    sh 'git remote set-url origin https://mcontrerass:X1Ex1en!@github.com/usach-devops/ms-iclab.git'
    sh "git push origin --delete ${branch}"
}

def createBranch(String origin, String newBranch) {
    echo "origin:  ${origin}"
    echo "newBranch:  ${newBranch}"

withCredentials([usernamePassword(credentialsId: env.GIT_CREDENTIAL_ID, usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        script {
            env.encodedPass=URLEncoder.encode(PASS, "UTF-8")
        }
        
        sh '''
        git remote set-url origin https://${USER}:${encodedPass}@github.com/usach-devops/ms-iclab.git
        
        git fetch -p
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git checkout -b '''+newBranch+'''
        git push origin '''+newBranch+'''
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git branch -d '''+newBranch+'''
    '''
 } 

  
    //git branch -d '''+newBranch+'''
}

return this;