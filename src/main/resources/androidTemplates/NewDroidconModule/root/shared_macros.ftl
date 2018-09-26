<#import "root://common/proguard_macros.ftl" as proguard>

<#-- Some common elements used in multiple files -->
<#macro generateManifest packageName hasApplicationBlock=false>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${packageName}"<#if !hasApplicationBlock>/</#if>><#if hasApplicationBlock>
    <application <#if minApiLevel gte 4 && buildApi gte 4>android:allowBackup="true"</#if>
        android:label="@string/app_name"<#if copyIcons>
        android:icon="@mipmap/ic_launcher"<#if buildApi gte 25 && targetApi gte 25>
        android:roundIcon="@mipmap/ic_launcher_round"</#if><#elseif assetName??>
        android:icon="@drawable/${assetName}"</#if><#if buildApi gte 17>
        android:supportsRtl="true"</#if>
        android:theme="@style/AppTheme"/>
</manifest></#if>
</#macro>

<#macro androidConfig hasTests=false>

android {
    compileSdkVersion androidCompileSdkVersion

    defaultConfig {

        minSdkVersion androidMinSdkVersion
        targetSdkVersion androidTargetSdkVersion
        versionCode 1
        versionName "1.0"

    <#if hasTests>
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    </#if>
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

<@proguard.proguardConfig />

}
</#macro>
