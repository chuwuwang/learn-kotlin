plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.ktx"
version = "1.1.3-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation( fileTree( mapOf("dir" to "libs", "include" to listOf("*.jar") ) ) )
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("commons-lang:commons-lang:2.6")
    // https://mvnrepository.com/artifact/org.jpos/jpos
    implementation("org.jpos:jpos:2.1.4")
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