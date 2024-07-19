import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.carenation"
version = "0.0.1-SNAPSHOT"
val queryDslVersion: String by extra


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // module 용 의존 주입
    implementation(project(":modules:application"))
    implementation(project(":modules:domain"))

    // lib
    implementation("org.springframework.boot:spring-boot-starter")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}
