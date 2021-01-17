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
    echo "origin:  ${origin}"
    echo "newBranch:  ${newBranch}"
    sh '''
        git remote set-url origin https://nicolashermosilla:50ed7c548a7b92631c8aa81577ef02b862e67b5b@github.com/usach-devops/ms-iclab.git
        
        git fetch -p
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git checkout -b '''+newBranch+'''
        git push origin '''+newBranch+'''
        git checkout '''+origin+'''; git pull origin '''+origin+''' 
        git branch -d '''+newBranch+'''
    '''
    //git branch -d '''+newBranch+'''
}

return this;