tasks.create<Copy>("build") {
    destinationDir = File("build")
    dependsOn(
        ":content:jsBrowserProductionWebpack",
    )
    into("TrueDLsite") {
        from("${rootProject.project(":content").buildDir}/dist/js/productionExecutable")
        from("src/resources")
    }
}
