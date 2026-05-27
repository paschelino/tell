plugins {
    kotlin("jvm") version "2.3.21"
    `jacoco`
}

group = "de.paschelino"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}

@Suppress("UnstableApiUsage")
tasks.withType(JavaCompile::class).configureEach {
    options.release = 21
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.12"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = false
        csv.required = false
        html.required = true
    html.outputLocation = layout.buildDirectory.dir("reports/jacoco/test/jacocoTestReport.html")
    }
}
