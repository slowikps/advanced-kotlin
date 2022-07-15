import org.codehaus.groovy.tools.shell.util.Logger.io

plugins {
    java
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.jfrog.org/oss-release-local")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    //It is probably safer to use plain java instead
    api("io.rsocket.kotlin:rsocket-core:0.15.4")
    api("io.rsocket.kotlin:rsocket-transport-okhttp:0.9.6")
    api("io.rsocket.kotlin:rsocket-transport-netty:0.9.6")

    implementation(platform("io.projectreactor:reactor-bom:Bismuth-RELEASE"))
    implementation("io.projectreactor:reactor-core")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")

    testImplementation("io.kotest", "kotest-runner-junit5", "5.3.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}