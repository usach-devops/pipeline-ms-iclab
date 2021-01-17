def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + validate.getValidBranchName()
}

def merge(branchfrom, branchto) {
    figlet 'git merge ' + branchfrom + ' -> ' branchto
    def merge_text = 'Merge '+branchfrom+' into '+branchto
    echo merge_text
    sh 'git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/usach-devops/ms-iclab.git'
    sh 'git checkout '+branchfrom 
    sh 'git pull origin '+branchfrom
    sh 'git checkout '+branchto
    sh 'git pull origin '+branchto
    sh 'git merge '+branchfrom
    sh 'git commit -am \" Merge '+branchfrom+' a '+branchto+' \"' + '|| true'
    sh 'git push origin '+branchto 
}

def tag(branchto,tagname) {
    figlet 'git tag'
    def merge_text = 'Tag a main con el nombre de '+tagname
    echo merge_text
    sh 'git remote set-url origin https://mcontrerass:X1Ex1en!@github.com/usach-devops/ms-iclab.git'
    sh 'git pull origin main'
    sh 'git checkout '+branchto
    sh 'git tag -a '+tagname+ ' -m  \" Etiquetado como  '+tagname+' \"'
    sh 'git push --tags'

}

def checkIfBranchExists(String branch) {
    
    def output = sh (script: "git ls-remote --heads origin ${branch}", returnStdout: true)
    echo 'branch existe: ' + output

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
        git checkout '''+origin+''' 
        git pull origin '''+origin+'''
        git checkout -b '''+newBranch+'''
        git push origin '''+newBranch+'''
        git checkout '''+origin+''' 
        git pull origin '''+origin+''' 
        git branch -d '''+newBranch+'''
    '''
}

return this