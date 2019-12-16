import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitPlatformVersion = "1.5.2"
val jupiterVersion = "5.5.2"

plugins {
    kotlin("jvm") version "1.3.60"
    `maven-publish`
}

group = "io.mbo.labs"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        register("mavenKotlin", MavenPublication::class) {
            artifact("build/libs/${rootProject.name}-$version.jar")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:$jupiterVersion")
    testImplementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
