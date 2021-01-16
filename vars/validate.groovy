
//Validar el tipo de rama a ejecutar (feature, develop o release)
//USO : validate.getBranchName()
def getBranchName() {
    def branches = ['develop', 'main']

    def branch = env.GIT_BRANCH

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

def isFeature(String branchName) {
    return branchName =~ /feature*/
}

//USO : if (validate.isBranchName('develop')) {}
def isBranchName(String branchName) {
    return branchName == env.GIT_BRANCH
}

//Validar formato de nombre de rama release según patrón
//release-v{major}-{minor}-{patch}
//USO :  if (validate.validateReleaseNameFormat('release-v1-0-0')) {  echo 'OK' }
def isRelease(String branchName) {
    return branchName =~ /(release-v[0-9]+)-([0-9]+)-([0-9]+)/
}
