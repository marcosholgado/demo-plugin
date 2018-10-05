<?xml version="1.0"?>
<recipe>
  <mkdir at="${escapeXmlAttribute(projectOut)}/libs" />
  <mkdir at="${escapeXmlAttribute(resOut)}/drawable" />
  <mkdir at="${escapeXmlAttribute(srcOut)}" />

  <merge from="root/settings.gradle.ftl" 
    to="${escapeXmlAttribute(topOut)}/settings.gradle" />

  <instantiate from="root/build.gradle.ftl" 
    to="${escapeXmlAttribute(projectOut)}/build.gradle" />

  <instantiate from="root/AndroidManifest.xml.ftl" 
    to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

  <instantiate from="root/res/values/strings.xml.ftl" 
    to="${escapeXmlAttribute(resOut)}/values/strings.xml" />
  
  <copy from="root/res/values/colors.xml" 
    to="${escapeXmlAttribute(resOut)}/values/colors.xml" />

  <copy from="root/res/values/styles.xml" 
    to="${escapeXmlAttribute(resOut)}/values/styles.xml" />

  <copy from="root://common/gitignore" 
    to="${escapeXmlAttribute(projectOut)}/.gitignore" />

  <copy from="root/res/layout/activity_main.xml" 
    to="${escapeXmlAttribute(resOut)}/layout/activity_main.xml" />

  <!-- Tests -->
  <instantiate from="root/test/app_package/ExampleInstrumentedTest.${ktOrJavaExt}.ftl" 
    to="${escapeXmlAttribute(testOut)}/ExampleInstrumentedTest.${ktOrJavaExt}" />

  <instantiate from="root/test/app_package/ExampleUnitTest.${ktOrJavaExt}.ftl" 
    to="${escapeXmlAttribute(unitTestOut)}/ExampleUnitTest.${ktOrJavaExt}" />  

  <!-- App files -->
  <instantiate from="root/src/app_package/MainActivity.kt.ftl" 
    to="${escapeXmlAttribute(srcOut)}/MainActivity.kt" /> 

  <instantiate from="root/src/app_package/application/App.kt.ftl" 
    to="${escapeXmlAttribute(srcOut)}/application/App.kt" /> 

  <!-- Navigation specifics -->
  <copy from="root/res/menu/navigation.xml" 
    to="${escapeXmlAttribute(resOut)}/menu/navigation.xml" />

  <copy from="root/res/navigation/nav_graph.xml" 
    to="${escapeXmlAttribute(resOut)}/navigation/nav_graph.xml" />

  <!-- Copy drawables -->
  <copy from="root/res/mipmap-anydpi-v26/ic_launcher.xml" 
    to="${escapeXmlAttribute(resOut)}/mipmap-anydpi-v26/ic_launcher.xml" />
  <copy from="root/res/drawable/ic_launcher_background.xml" 
    to="${escapeXmlAttribute(resOut)}/drawable/ic_launcher_background.xml" />
  <copy from="root/res/drawable-v24/ic_launcher_foreground.xml" 
    to="${escapeXmlAttribute(resOut)}/drawable-v24/ic_launcher_foreground.xml" />
  <copy from="root/res/mipmap-anydpi-v26/ic_launcher_round.xml"
    to="${escapeXmlAttribute(resOut)}/mipmap-anydpi-v26/ic_launcher_round.xml" />            

  <#include "root://common/proguard_recipe.xml.ftl"/>

</recipe>
