def diff(){
    figlet 'git diff'
    sh 'git pull'
    sh 'git status'
    sh 'git branch'
    sh 'git remote -v'
    sh 'git clean -f;git pull'
    sh 'git checkout -f main; git pull'
    sh 'git diff origin/main..origin/develop --'
    // sh 'git checkout main; git pull'
    // sh 'git checkout '+ env.GIT_BRANCH +'; git pull'
    // sh 'git diff main..' + env.GIT_BRANCH +' --'
}
return this