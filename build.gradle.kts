buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath("com.android.tools.build:gradle:4.1.0")
    }
}
group = "com.multiplatform.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
