tasks.create<Copy>("build") {
    destinationDir = File("build")
    dependsOn(":content:jsBrowserProductionWebpack", ":popup:jsBrowserProductionWebpack")
    into("generated") {
        from("${rootProject.project(":content").buildDir}/dist/js/productionExecutable")
        from("${rootProject.project(":popup").buildDir}/dist/js/productionExecutable")
        from("src/resources")
    }
}
