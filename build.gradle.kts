plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.5.5"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

group = "com.parakeetstudios"
version = "1.0-SNAPSHOT"
description = "Tile-Based-Boardgame-Plugin"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.inject:guice:6.0.0")
    implementation("com.google.inject.extensions:guice-assistedinject:4.2.3")
    compileOnly("org.projectlombok:lombok:1.18.20")
    compileOnly("org.projectlombok:lombok:1.18.20")
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}