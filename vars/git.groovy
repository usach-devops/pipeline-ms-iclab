def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + env.GIT_BRANCH
}

def merge(branchfrom, branchto) {
    figlet 'git merge'
    echo 'Merge '+branchfrom+' into '+branchto
    sh 'git checkout '+branchto
    sh 'git merge origin/' + branchfrom
}

return this