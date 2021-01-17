def execute() {
    def tool = validate.getBuildTool()

    if (tool == 'gradle') {
        env.BUILD_TOOL = 'gradle'
        println env.BUILD_TOOL
        gradleci.execute()
    } else {
        env.BUILD_TOOL = 'maven'
        println env.BUILD_TOOL
        mavenci.execute()
    }
}
return this
