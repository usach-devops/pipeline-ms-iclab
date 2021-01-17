
//Validar el tipo de rama a ejecutar (feature, develop o release)
//USO : validate.getBranchName()
def getBranchName() {
    def branches = ['develop', 'main']

    def branch = getValidBranchName()

    if (branches.contains(branch)) {
        return branch
    }

    if (isFeature(branch)) {
        return 'feature'
    }

    if (isRelease(branch)) {
        return 'release'
    }
}

def getValidBranchName() {
    def branchsplit = env.GIT_BRANCH.split("/")
    return branchsplit.length == 1 ? branchsplit[0] : branchsplit[1]
}

def isFeature(String branchName) {
    return branchName =~ /feature*/
}

//USO : if (validate.isBranchName('develop')) {}
def isBranchName(String branchName) {
    //return branchName == env.GIT_BRANCH
    return branchName == getValidBranchName()
}

//Validar formato de nombre de rama release según patrón
//release-v{major}-{minor}-{patch}
//USO :  if (validate.validateReleaseNameFormat('release-v1-0-0')) {  echo 'OK' }
def isRelease(String branchName) {
    //return branchName =~ /^(*\/release-v[0-9]+)\-([0-9]+)\-([0-9]+)?$/
    return branchName =~ /(release-v[0-9]+)-([0-9]+)-([0-9]+)/
}

def getTech() {
   def tech = ['ms', 'front', 'bff']
	def repo = env.GIT_URL //.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
	//println "Repo: ${repo}"

	for (item in tech) {
		//println "Item: ${item}"
    	if (repo.contains(item)) {
			return item
		}
	}
}