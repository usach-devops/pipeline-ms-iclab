def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + validate.getValidBranchName()
}

def merge(branchfrom, branchto) {
    figlet 'git merge'
    def merge_text = 'Merge '+branchfrom+' into '+branchto
    echo merge_text
    sh 'git remote set-url origin https://mcontrerass:X1Ex1en!@github.com/usach-devops/ms-iclab.git'
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

return this