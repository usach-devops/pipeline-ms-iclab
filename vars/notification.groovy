//Mensaje por defecto, indicando maven/gradle y el step donde se produjo el error
//por cada step se tiene que agregar env.JENKINS_STAGE = env.STAGE_NAME
def sendFailureMsg() {
    def MSG_BASE = "Build Failure: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}] [Stage ${env.JENKINS_STAGE}]";
    slackSend color: "danger", message: "${MSG_BASE}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def sendFailureMsg(String msg) {
    slackSend color: "danger", message: "Build Failure: ${msg}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

//Mensaje por defecto, indicando maven/gradle y el build success
def sendSuccessMsg() {
    def MSG_BASE = "Build Success: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}]";
    slackSend color: "good", message: "${MSG_BASE}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def sendSuccessMsg(String msg) {
    slackSend color: "good", message: "Build Success: ${msg}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}