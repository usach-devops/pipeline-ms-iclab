def diff(){
    figlet env.GIT_BRANCH
    sh 'git diff origin/main..' + env.GIT_BRANCH
}
return this