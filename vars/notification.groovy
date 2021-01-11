//Mensaje por defecto, indicando maven/gradle y el step donde se produjo el error
//por cada step se tiene que agregar env.JENKINS_STAGE = env.STAGE_NAME
def failure() {
    def template = "Build Failure: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}] [Stage ${env.JENKINS_STAGE}]";
    slackSend color: "danger", message: "${template}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def failure(msg) {
    slackSend color: "danger", message: "Build Failure: ${msg}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success() {
    def template = "Build Success: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}]";
    slackSend color: "good", message: "${template}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success(msg) {
    slackSend color: "good", message: "Build Success: ${msg}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}