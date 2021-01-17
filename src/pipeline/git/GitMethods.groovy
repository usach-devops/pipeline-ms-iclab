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
    sh "git push origin --delete ${branch}"
}

def createBranch(String origin, String newBranch) {
    sh '''
        git branch -d '''+newBranch+'''
        git fetch -p
        git checkout '''+origin+'''; git pull
        git checkout -b '''+newBranch+'''
        git remote set-url origin https://mcontrerass:X1Ex1en!@github.com/usach-devops/pipeline-ms-iclab.git
        git push origin '''+newBranch+'''
        git checkout '''+origin+'''; git pull
        git branch -d '''+newBranch+'''
    '''
}

return this;