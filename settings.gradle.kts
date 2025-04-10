pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

//rootProject.name = "NBP Currency Viewer"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:navigation")
include(":core:ui")
include(":core:nbpapi:data")
include(":core:nbpapi:domain")
include(":feature:currencyList:ui")
include(":feature:currencyList:domain")
include(":feature:currencyList:data")
include(":feature:currencyDetails:ui")
include(":feature:currencyDetails:domain")
include(":feature:currencyDetails:data")
