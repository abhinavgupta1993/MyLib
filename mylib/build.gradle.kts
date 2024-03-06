plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.abhinav.mylib"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            from(components["java"])
//            groupId = "com.abhinav.mylib"
//            artifactId = "mylib"
//            version = "1.0.0"
//            artifact("$buildDir/outputs/aar/mylib-release.aar")
//        }
//    }
//    repositories {
//        maven {
//        name = "GithubPackages"
//        url = uri("https://maven.pkg.github.com/abhinavgupta1993/MyLib")
////        maven {
////            url = uri("https://your-repo-url")
//            credentials {
//                username = "abhinavgupta1993"
//                password = "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"
//            }
//        }
//    }
//}

subprojects {
    apply(plugin = "maven-publish")
    configure<PublishingExtension> {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/abhinavgupta1993/MyLib")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}