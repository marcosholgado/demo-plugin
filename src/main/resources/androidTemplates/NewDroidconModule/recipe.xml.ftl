<?xml version="1.0"?>
<recipe>
    <mkdir at="${escapeXmlAttribute(projectOut)}/libs" />
    <mkdir at="${escapeXmlAttribute(resOut)}/drawable" />

    <merge from="root/settings.gradle.ftl"
             to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/build.gradle.ftl"
                   to="${escapeXmlAttribute(projectOut)}/build.gradle" />

    <instantiate from="root/AndroidManifest.xml.ftl"
                   to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/res/values/strings.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

    <instantiate from="root/test/app_package/ExampleInstrumentedTest.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(testOut)}/ExampleInstrumentedTest.${ktOrJavaExt}" />

    <instantiate from="root/test/app_package/ExampleUnitTest.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(unitTestOut)}/ExampleUnitTest.${ktOrJavaExt}" />
    
    <dependency mavenUrl="libraries.jUnit" gradleConfiguration="testImplementation" />
    <dependency mavenUrl="libraries.testRunner" gradleConfiguration="androidTestImplementation" />
    <dependency mavenUrl="libraries.espressoCore" gradleConfiguration="androidTestImplementation" />

    <mkdir at="${escapeXmlAttribute(srcOut)}" />

    <copy from="root://common/gitignore"
            to="${escapeXmlAttribute(projectOut)}/.gitignore" />

    <#include "root://common/proguard_recipe.xml.ftl"/>

</recipe>
