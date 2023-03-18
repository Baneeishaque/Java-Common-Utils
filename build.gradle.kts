plugins {

    `java-library`
    `maven-publish`
    signing
}

group = "com.github.baneeishaque"
version = "1.0"

tasks.register<Jar>("sourcesJar") {

    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {

    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

publishing {

    publications {

        create<MavenPublication>("mavenJava") {

            artifactId = "utils_j2se"
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("Java Common Utils")
                description.set("Java Common Utils.")
                url.set("https://github.com/Baneeishaque/Java_Common_Utils")

                licenses {

                    license {

                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {

                    developer {

                        id.set("baneeishaque")
                        name.set("Banee Ishaque K")
                        email.set("baneeishaque@gmail.com")
                    }
                }

                scm {

                    connection.set("scm:git:git://github.com/Baneeishaque/Java_Common_Utils.git")
                    developerConnection.set("scm:git:ssh://git@github.com:Baneeishaque/Java_Common_Utils.git")
                    url.set("https://github.com/Baneeishaque/Java_Common_Utils")
                }
            }
        }
    }

    repositories {

        maven {

            val releasesRepoUrl =
                    uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

            credentials {

                username = "baneeishaque"
                password = "-CPdnC9dKp-nzLt"
            }
        }
    }
}

signing {

    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {

    if (JavaVersion.current().isJava9Compatible) {

        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

repositories {

//    mavenCentral()
}

dependencies {

    implementation("org.json:json:20230227")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    testImplementation("junit", "junit", "4.13.2")
}

configure<JavaPluginExtension> {

    sourceCompatibility = JavaVersion.VERSION_1_8
}
