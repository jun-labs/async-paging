import java.util.Properties

//val properties = Properties().apply {
//    load(file("gradle.properties").inputStream())
//}


pluginManagement {
    val springBootVersion: String by settings
    val kotlinVersion: String by settings
    val hibernateValidatorVersion: String by settings
    val kotlinxCoroutinesVersion: String by settings
    val queryDslVersion: String by settings
    val jvmTargetVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotestVersion: String by settings
    val mockitoKotlinVersion: String by settings
    val junitVersion: String by settings
    val testContainersVersion: String by settings
    val flapdoodleMongoVersion: String by settings
    val jpaVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.allopen",
                "org.jetbrains.kotlin.plugin.spring",
                "org.jetbrains.kotlin.kapt",
                -> useVersion(kotlinVersion)

                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(jpaVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}

rootProject.name = "paging-async"
