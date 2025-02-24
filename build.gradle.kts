plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.dokkaHtml {
    dokkaSourceSets {
        named("main") {
            sourceRoots.from(project.file("src/main/kotlin"))
        }
        create("tests") {
            sourceRoots.from(project.file("src/test/kotlin"))
        }
        create("extras") {
            sourceRoots.from(project.file("src/extras/kotlin"))
        }
    }
}