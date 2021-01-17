def diff(){
    figlet 'git diff'
    sh 'git status'
    // sh 'git checkout main; git pull'
    // sh 'git checkout '+ env.GIT_BRANCH +'; git pull'
    // sh 'git diff main..' + env.GIT_BRANCH +' --'
}
return this