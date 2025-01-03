plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    mavenCentral()
}

java.setTargetCompatibility(22)

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_22
    }
}
tasks {
    sourceSets {
        main {
            kotlin.srcDirs("src")
        }
    }
    wrapper {
        gradleVersion = "8.11.1"
    }
}