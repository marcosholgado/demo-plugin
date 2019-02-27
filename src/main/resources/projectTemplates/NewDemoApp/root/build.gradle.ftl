<#import "./shared_macros.ftl" as shared>
<#import "root://common/kotlin_macros.ftl" as kt>

apply plugin: 'com.android.application'
<@kt.addKotlinPlugins />

apply plugin: "androidx.navigation.safeargs"
apply from: '../dependencies.gradle'

<@shared.androidConfig hasTests=true/>

dependencies {
    implementation project(path: ':core')

	implementation libraries.navigationFragment
    implementation libraries.navigationUI

    testImplementation libraries.jUnit
    androidTestImplementation libraries.testRunner
    androidTestImplementation libraries.espressoCore
}
