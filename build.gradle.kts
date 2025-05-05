plugins {
    kotlin("jvm") version "2.1.20"
    jacoco
}
jacoco {
    toolVersion = "0.8.10" // Latest version
}
group = "org.madrid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // TEST
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // KOIN
    implementation("io.insert-koin:koin-core:3.5.3")
    // JUPITER
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    // TRUTH
    testImplementation("com.google.truth:truth:1.4.4")
    // MOCKK
    testImplementation("io.mockk:mockk:1.14.0")
    // JSON
    implementation("com.google.code.gson:gson:2.10.1")
    // SERIALIZATION
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // Ktor
    implementation("io.ktor:ktor-client-android:3.1.2")
    implementation("io.ktor:ktor-client-cio:3.1.2")
    implementation("io.ktor:ktor-client-content-negotiation:3.1.2")
    implementation("io.ktor:ktor-client-core:3.1.2")
    implementation("io.ktor:ktor-client-serialization:3.1.2")
    implementation("io.ktor:ktor-client-okhttp:3.1.2")

}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

}
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }
    }
}

kotlin {
    jvmToolchain(23)
}
