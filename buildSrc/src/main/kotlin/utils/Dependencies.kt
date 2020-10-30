package utils

object ApplicationConfig {
    const val id = "com.rods.mymarvelproject"
    const val versionCode = 1
    const val versionName = "1.0"

    object Build {
        const val minSdkVersion = 21
        const val compileSdkVersion = 29
        const val buildToolsVersion = "30.0.2"
    }
}

object Dependencies {
    object Core {
        private object Versions {
            const val kotlin= "1.4.10"
            const val gradle= "4.1.0"
        }
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val std_kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val gradle_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object AndroidX {
        private object Versions {
            const val core = "1.3.2"
            const val appcompat = "1.2.0"
            const val constraintlayout = "2.0.4"
        }

        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    }

    object Google {
        private object Versions {
            const val material = "1.2.1"
        }

        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object IO {
        private object Versions {
            const val retrofit = "2.9.0"
        }

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    }

    object Testing {
        private object Versions {
            const val junit = "4.13.1"
            const val espresso_core = "3.3.0"
            const val androidx_junit_ext = "1.1.2"
        }

        const val junit = "junit:junit:${Versions.junit}"
        const val junit_ext = "androidx.test.ext:junit:${Versions.androidx_junit_ext}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    }
}