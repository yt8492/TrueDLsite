plugins {
    kotlin("multiplatform")
}

group = "com.yt8492"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            webpackTask {
                mainOutputFileName.set("option.js")
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}