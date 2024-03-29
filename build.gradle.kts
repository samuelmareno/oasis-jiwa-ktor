val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}
    val tcnative_version = "2.0.44.Final"
    val osName = System.getProperty("os.name").toLowerCase()
    val tcnative_classifier = if (osName.contains("win")) {
         "windows-x86_64"
    } else if (osName.contains("linux")) {
        "linux-x86_64"
    } else if (osName.contains("mac")) {
        "osx-x86_64"
    } else {
        ""
    }

group = "com.injilkeselamatan"
version = "0.0.3"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

tasks {
    shadowJar {

        manifest {
            attributes(Pair("Main-Class", "com.injilkeselamatan.ApplicationKt"))
        }
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    testImplementation("io.ktor:ktor-network-tls-certificates:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //Koin
    implementation("io.insert-koin:koin-ktor:3.1.4")

    // KMongo
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")

    // OpenSSL bindings
    implementation ("io.netty:netty-tcnative:$tcnative_version")
    implementation ("io.netty:netty-tcnative-boringssl-static:$tcnative_version")
    implementation ("io.netty:netty-tcnative-boringssl-static:$tcnative_version:$tcnative_classifier")
}