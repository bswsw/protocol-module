import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

val javaxAnnotationApiVersion by extra { "1.3.2" }
val protobufVersion by extra { "3.11.0" }
val grpcVersion by extra { "1.26.0" }
val ktLintVersion by extra { "0.35.0" }
val junitVersion by extra { "5.5.2" }

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.google.protobuf") version "0.8.10"

    `java-library`
    `maven-publish`
    idea
}

group = "com.baegoon"
version = "1.0.0"

repositories {
    mavenCentral()
}

val ktLintDependency: Configuration by configurations.creating

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    api("io.grpc:grpc-protobuf:$grpcVersion")
    api("io.grpc:grpc-stub:$grpcVersion")
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
    implementation("javax.annotation:javax.annotation-api:$javaxAnnotationApiVersion")

    ktLintDependency("com.pinterest:ktlint:$ktLintVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {
    val ktLint by registering(JavaExec::class) {
        classpath = ktLintDependency
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
        description = "Check Kotlin code style."
    }

    register<JavaExec>("ktlintFormat") {
        classpath = ktLintDependency
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
        description = "Fix Kotlin code style deviations."
    }

    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }

        dependsOn(ktLint)
    }

    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    test {
        useJUnitPlatform()
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("http://localhost:8081/repository/maven-releases")
            credentials {
                username = "admin"
                password = "password"
            }
        }
    }
}
