def execute() {
    def tool = buildtool.get()

    if (tool == 'gradle') {
        env.BUILD_TOOL = 'gradle'
        println 'run gradle ci'
    } else {
        env.BUILD_TOOL = 'maven'
        println 'run maven ci'
    }
}
return this
