def template = "[Grupo2][Pipeline ${env.PIPELINE_TYPE}][Rama: ${validate.getValidBranchName()}][Stage: ${env.JENKINS_STAGE}][Proyecto ${env.BUILD_TOOL}]"

//Mensaje por defecto, indicando maven/gradle y el step donde se produjo el error
//por cada step se tiene que agregar env.JENKINS_STAGE = env.STAGE_NAME
def failure() {
    def template = "Build Failure: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}] [Stage ${env.JENKINS_STAGE}]"
    slackSend color: 'danger', message: "${template}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def failure(msg) {
    def template = "Build Failure: [devops-usach-grupo2][Proyecto ${env.BUILD_TOOL}]"
    slackSend color: 'danger', message: "Build Failure: ${template} ${msg}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success() {
    def message= notification.template  + '[Resultado: Ok]'
    slackSend color: 'good', message: message, teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success(msg) {
    def message= notification.template + '[Resultado: Ok]'
    slackSend color: 'good', message: message, teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}
