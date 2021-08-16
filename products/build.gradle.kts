plugins {
    id("com.transaction.spring-common-conventions")
    idea
}

repositories {
    mavenLocal()
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
    google()
}

dependencies {
    implementation(project(":cartcommons"))
}