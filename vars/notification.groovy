
//Mensaje por defecto, indicando maven/gradle y el step donde se produjo el error
//por cada step se tiene que agregar env.JENKINS_STAGE = env.STAGE_NAME
def failure() {
    def template = "[Grupo2][Pipeline ${env.PIPELINE_TYPE}][Rama: ${validate.getValidBranchName()}][Stage: ${env.JENKINS_STAGE}][Proyecto ${env.BUILD_TOOL}][Resultado: No Ok]"
    slackSend color: 'danger', message: "${template}", teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def failure(msg) {
    def template = "[Grupo2][Pipeline ${env.PIPELINE_TYPE}][Rama: ${validate.getValidBranchName()}][Stage: ${env.JENKINS_STAGE}][Proyecto ${env.BUILD_TOOL}][Resultado: No Ok][Detalle: ${msg}]"
    slackSend color: 'danger', message: template, teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success() {
    def template = "[Grupo2][Pipeline ${env.PIPELINE_TYPE}][Rama: ${validate.getValidBranchName()}][Stage: ${env.JENKINS_STAGE}][Proyecto ${env.BUILD_TOOL}][Resultado: Ok]"
    slackSend color: 'good', message: template, teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}

def success(msg) {
    def template = "[Grupo2][Pipeline ${env.PIPELINE_TYPE}][Rama: ${validate.getValidBranchName()}][Stage: ${env.JENKINS_STAGE}][Proyecto ${env.BUILD_TOOL}][Resultado: Ok][Detalle: ${msg}]"
    slackSend color: 'good', message: template, teamDomain: 'devops-usach-2020', tokenCredentialId: 'slack-credentials'
}
