plugins {
    java
    kotlin("jvm") version "1.3.71"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.jfrog.org/oss-release-local")

}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    //It is probably safer to use plain java instead
    compile("io.rsocket.kotlin:rsocket-core:0.9.6")
    compile("io.rsocket.kotlin:rsocket-transport-okhttp:0.9.6")
    compile("io.rsocket.kotlin:rsocket-transport-netty:0.9.6")

    implementation(platform("io.projectreactor:reactor-bom:Bismuth-RELEASE"))
    implementation("io.projectreactor:reactor-core")

    testCompile("org.junit.jupiter", "junit-jupiter-engine", "5.6.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}