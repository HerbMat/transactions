plugins {
    id("com.transaction.spring-common-conventions")
    idea
}

idea {
    module {
        // proto files and generated Java files are automatically added as
        // source dirs.
        // If you have additional sources, add them here:
        sourceDirs.add(file("build/generated/source/proto/main/java"));
        sourceDirs.add(file("build/generated/source/proto/main/kotlin"));
    }
}

dependencies {
    implementation(project(":cartcommons"))
}