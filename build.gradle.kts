plugins {
    id("java")
    id("org.springframework.boot") version "2.7.8"
}

apply(plugin = "io.spring.dependency-management")

group = "ru.kpfu.itis"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    testImplementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    //db
    implementation("org.postgresql:postgresql:42.5.3")

    //lombok
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    //webjars
    implementation ("org.webjars:jquery:3.6.0")
    implementation ("org.webjars:bootstrap:4.6.0")
    implementation ("org.webjars:webjars-locator-core:0.46")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
