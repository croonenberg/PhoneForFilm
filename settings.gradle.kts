// Top-level build file where you can add configuration options common to all sub-projects/modules.

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // Fail if any project tries to declare repositories on its own
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Set the name of the root project
rootProject.name = "PhoneForFilm"

// Include application and library modules
include(":app")
include(":phoneforfilm-data")
include(":phoneforfilm-view")
// TODO: include additional modules as needed, e.g. :phoneforfilm-common, :phoneforfilm-domain
