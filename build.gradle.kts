// plugins {
//    id("org.springframework.boot") version "3.3.1"
//    id("io.spring.dependency-management") version "1.1.5"
//    kotlin("plugin.jpa") version "1.9.24"
//    kotlin("jvm") version "1.9.24"
//    kotlin("plugin.spring") version "1.9.24"
//    //querydsl
//    kotlin("kapt") version "1.7.10"
//
//
// allOpen {
//    annotation("jakarta.persistence.Entity")
//    annotation("jakarta.persistence.Embeddable")
//    annotation("jakarta.persistence.MappedSuperclass")
// }
//
//
// group = "com.carenation"
// version = "0.0.1-SNAPSHOT"
//
// java {
//    toolchain {
//        languageVersion = JavaLanguageVersion.of(17)
//    }
// }
//
// repositories {
//    mavenCentral()
// }
//
// dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("com.mysql:mysql-connector-j")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//    // QueryDSL 의존성 추가
//    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
//    implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
//    implementation("jakarta.persistence:jakarta.persistence-api")
//    implementation("jakarta.annotation:jakarta.annotation-api")
//
//    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
//    kapt("org.springframework.boot:spring-boot-configuration-processor")
//
//    // MapStruct 추가
//    implementation("org.mapstruct:mapstruct:1.5.2.Final")
//    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
//    kaptTest("org.mapstruct:mapstruct-processor:1.5.2.Final")
//
//
// }
//
//
// tasks.withType<Test> {
//    useJUnitPlatform()
// }
//
