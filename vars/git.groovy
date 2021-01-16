def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..origin/' + env.GIT_BRANCH
}
return this