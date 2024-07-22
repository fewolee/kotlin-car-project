plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}




group = "com.carenation"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // 모듈 의존
    implementation(project(":modules:application"))
    implementation(project(":modules:domain"))
    implementation(project(":modules:adapter-data-jpa"))
    implementation(project(":modules:connector"))
    // 스프링 부트 실행
    implementation("org.springframework.boot:spring-boot-starter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

