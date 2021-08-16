plugins {
    id("com.transaction.spring-common-conventions")
}

dependencies {
    implementation(project(":cartcommons"))
    implementation(project(":paymentcommons"))
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.projectreactor.kafka:reactor-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}