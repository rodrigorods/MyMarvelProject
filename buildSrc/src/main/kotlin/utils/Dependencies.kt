package utils

object ApplicationConfig {
    const val id = "com.rods.mymarvelproject"
    const val versionCode = 1
    const val versionName = "1.0"

    object Build {
        const val minSdkVersion     = 21
        const val compileSdkVersion = 29
        const val buildToolsVersion = "30.0.2"
    }
}

object Dependencies {
    object Core {
        private object Versions {
            const val kotlin= "1.4.10"
            const val coroutine= "1.3.9"
            const val gradle= "4.1.0"
        }
        const val gradle        = "com.android.tools.build:gradle:${Versions.gradle}"
        const val std_kotlin    = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val gradle_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
        const val coroutine     = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    }

    object AndroidX {
        private object Versions {
            const val core              = "1.3.2"
            const val appcompat         = "1.2.0"
            const val constraintlayout  = "2.0.4"
            const val liveData          = "2.2.0"
            const val viewModel         = "2.2.0"
        }

        const val core              = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat         = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintlayout  = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val liveData          = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
        const val viewModel          = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    }

    object Navigation {
        object Versions {
            const val navigation = "2.3.1"
        }

        const val ui            = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment      = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val runtime       = "androidx.navigation:navigation-runtime-ktx::${Versions.navigation}"
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
            const val log_interceptor = "4.2.1"
        }

        const val retrofit          = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson_converter    = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val log_interceptor    = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    }

    object Koin {
        private object Versions {
            const val koin = "2.1.6"
        }

        const val core          = "org.koin:koin-core:${Versions.koin}"
        const val viewModel     = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val android_scope     = "org.koin:koin-android-scope:${Versions.koin}"
        const val android_ext     = "org.koin:koin-android-ext:${Versions.koin}"
        const val test          = "org.koin:koin-test:${Versions.koin}"
    }

    object Room {
        private object Versions {
            const val room = "2.2.5"
        }

        const val runtime          = "androidx.room:room-runtime:${Versions.room}"
        const val ktx              = "androidx.room:room-ktx:${Versions.room}"
        const val compiler         = "androidx.room:room-compiler:${Versions.room}"
    }

    object Glide {
        private object Versions {
            const val glide = "4.11.0"
        }

        const val core            = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler        = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Testing {
        private object Versions {
            const val junit = "4.13.1"
            const val android_core = "2.1.0"
            const val espresso_core = "3.3.0"
            const val recyclerview_action = "3.1.0"
            const val fragment_scenario = "1.2.5"
            const val androidx_junit_ext = "1.1.2"
            const val webserver = "3.1.2"
        }

        const val junit         = "junit:junit:${Versions.junit}"
        const val junit_ext     = "androidx.test.ext:junit:${Versions.androidx_junit_ext}"
        const val android_core     = "androidx.arch.core:core-testing:${Versions.android_core}"
        const val espresso_core         = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
        const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.recyclerview_action}"
        const val fragment_scenario = "androidx.fragment:fragment-testing:${Versions.fragment_scenario}"
        const val mock_webserver = "com.squareup.okhttp3:mockwebserver:${Versions.webserver}"
    }

    object Mockk {
        private object Versions {
            const val core = "1.10.2"
        }

        const val core = "io.mockk:mockk:${Versions.core}"
        const val android = "io.mockk:mockk-android:${Versions.core}"
    }
}