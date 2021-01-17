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
    sh 'git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/usach-devops/ms-iclab.git'
    sh "git push origin --delete ${branch}"
}

def createBranch(String origin, String newBranch) {
    echo "origin:  ${origin}"
    echo "newBranch:  ${newBranch}"

 

        sh '''
        git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/usach-devops/ms-iclab.git
        
        git fetch -p
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git checkout -b '''+newBranch+'''
        git push origin '''+newBranch+'''
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git branch -d '''+newBranch+'''
    '''
}

return this;