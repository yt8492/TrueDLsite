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
                mainOutputFileName.set("content.js")
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-js:1.0.0-pre.721")
            }
        }
    }
}
