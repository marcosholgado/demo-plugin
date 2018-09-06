<#import "./shared_macros.ftl" as shared>
<#import "root://common/kotlin_macros.ftl" as kt>

apply plugin: 'com.android.library'
apply from: '../dependencies.gradle'
<@kt.addKotlinPlugins />

<@shared.androidConfig hasTests=true/>

dependencies {
    ${getConfigurationName("compile")} fileTree(dir: 'libs', include: ['*.jar'])
    implementation project(path: ':core')

    <@shared.watchProjectDependencies/>
}
