package pipeline.test
//Validar el tipo de tecnología de la aplicación (ms, front, bff, etc)
//USO : validateTech.get()
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

def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    return matcher ? matcher[0][1] : null
}

return this;