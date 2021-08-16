plugins {
    id("com.transaction.quarkus-common-conventions")
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("io.quarkus:io.quarkus.gradle.plugin:1.13.3.Final")
    }
}


dependencies {
    implementation(project(":paymentcommons"))
}