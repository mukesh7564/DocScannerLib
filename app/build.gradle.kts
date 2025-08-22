plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    namespace = "com.github.mukesh7564.docscanner"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation("androidx.camera:camera-core:1.3.1")
    implementation("androidx.camera:camera-camera2:1.3.1")
    implementation("androidx.camera:camera-lifecycle:1.3.1")
    implementation("androidx.camera:camera-view:1.3.1")
}

afterEvaluate {
    extensions.getByType(org.gradle.api.publish.PublishingExtension::class.java).publications {
        create<MavenPublication>("release") {
            from(components["release"])
            groupId = "com.github.mukesh7564"
            artifactId = "DocScannerLib"
            version = "1.0.0"
        }
    }

    extensions.getByType(org.gradle.api.publish.PublishingExtension::class.java).repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mukesh7564/DocScannerLib")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
