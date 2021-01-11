def execute() {
    def tool = buildtool.get()

    if (tool == 'gradle') {
        println 'run gradle ci'
        } else {
        println 'run maven ci'
    }
}
return this
