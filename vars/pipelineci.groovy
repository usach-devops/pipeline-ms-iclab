def execute() {
    def tool = buildtool.get()

    if (tool == 'gradle') {
        gradleci.execute();
    } else {
        mavenci.execute();
    }
}
return this
