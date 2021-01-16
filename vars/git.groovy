def diff(){
    figlet 'git diff'
    sh 'git diff main..' + env.GIT_BRANCH +' --'
}
return this