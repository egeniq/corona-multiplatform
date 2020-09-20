import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.3.72"
    id("com.android.library")
    id("kotlin-android-extensions")
}
group = "com.multiplatform.sample"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven ("https://dl.bintray.com/icerockdev/moko")
}

val ktor_version = "1.3.2"
val serialization_version = "0.14.0"
val coroutines_version = "1.3.7"

kotlin {

//    jvm {
//        val main by compilations.getting {
//            kotlinOptions {
//                // Setup the Kotlin compiler options for the 'main' compilation:
//                jvmTarget = "1.8"
//            }
//
//            compileKotlinTask // get the Kotlin task 'compileKotlinJvm'
//            output // get the main compilation output
//        }
//    }
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
                api("dev.icerock.moko:mvvm:0.7.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("io.ktor:ktor-client-android:$ktor_version")
                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
                implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.12")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor_version")
                implementation("io.ktor:ktor-client-json-native:$ktor_version")
                implementation("io.ktor:ktor-client-logging-native:$ktor_version")
                implementation("io.ktor:ktor-client-serialization-native:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5-native-nt")

            }
        }
        val iosTest by getting
    }
}
android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}
val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)