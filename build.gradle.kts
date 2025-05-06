plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.ktx"
version = "1.1.6-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val pair = "include" to listOf("*.jar")
    val map = mapOf("dir" to "libs", pair)
    val fileTree = fileTree(map)
    implementation(fileTree)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("commons-lang:commons-lang:2.6")
    // https://mvnrepository.com/artifact/org.jpos/jpos
    implementation("org.jpos:jpos:2.1.4")
    // https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on
    implementation("org.bouncycastle:bcprov-jdk18on:1.78")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}