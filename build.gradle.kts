plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("com.github.johnrengelman.shadow") version "7.1.1"
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
    implementation("com.google.inject.extensions:guice-assistedinject:6.0.0")
    compileOnly("org.projectlombok:lombok:1.18.20")
    compileOnly("org.projectlombok:lombok:1.18.20")
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}

//val copyJar by tasks.register<Copy>("copyJar") {
//    from("$buildDir/libs") {
//        exclude("*-dev.jar")
//        rename { "TileScapeR1.jar" }
//    }
//    into("C:/Users/Cameron/Desktop/CODING/Minecraft Development/Parakeet Studios/Build Server/plugins")
//}

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

tasks.shadowJar {
    // helper function to relocate a package into our package
    fun reloc(pkg: String) = relocate(pkg, "com.parakeetstudios.tilescape.dependency.$pkg")

    // relocate cloud and it's transitive dependencies
    reloc("com.google.inject")
}