plugins {
    id("java")
    kotlin("jvm") version "2.1.10"
    id("com.gradleup.shadow") version("8.3.0")
}

val minestomVersion: String by project
val logbackVersion: String by project
val jlineVersion: String by project
val jsonVersion: String by project

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:$minestomVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("org.jline:jline:$jlineVersion")
	implementation("org.json:json:$jsonVersion")
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "delta.cion.msnt.Server"
        }
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        mergeServiceFiles()
        archiveClassifier.set("")
    }
}
