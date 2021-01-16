def diff(){
   sh 'git diff origin/main..' + env.GIT_BRANCH
}
return this