pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GitAndroid"
include(":app")
include(":core-network")
include(":core-datastore")
include(":core-data")
include(":core-domain")
include(":core-model")
include(":core-database")
