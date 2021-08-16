import org.jetbrains.kotlin.cli.jvm.main

plugins {
    id("com.transaction.quarkus-mongo-conventions")
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("io.quarkus:io.quarkus.gradle.plugin:1.13.3.Final")
    }
}

tasks.register("debugSourceSet") {
    sourceSets.asMap.forEach { (_, u) ->
        run {
            println("SOURCESET: $u")
            if (u.resources.sourceDirectories.elements.get().size > 1) {
                println("### ATTENTION!!! more than 1 resource source directory")
                u.resources.sourceDirectories.elements.get().forEach {
                    println("RESOURCE ELEMENT: ${it.asFile}")
                }
            } else {
                println("SINGLE: ${u.resources.sourceDirectories.singleFile.absolutePath}")
            }
            println("---")
            println("")
        }
    }
}