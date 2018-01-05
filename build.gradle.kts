import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.paschelino"
version = "0.0.1-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.10"

    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.2")
    }

}

apply {
    plugin("kotlin")
    plugin("org.junit.platform.gradle.plugin")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlin_version))
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.2")
    testCompile("org.junit.jupiter:junit-jupiter-engine:5.0.2")
    testCompile("org.hamcrest:hamcrest-junit:2.0.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
