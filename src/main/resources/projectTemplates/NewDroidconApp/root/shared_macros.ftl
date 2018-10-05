<#import "root://common/proguard_macros.ftl" as proguard>

<#-- Some common elements used in multiple files -->
<#macro generateManifest packageName hasApplicationBlock=false>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${packageName}"<#if !hasApplicationBlock>/</#if>><#if hasApplicationBlock>
    <application <#if minApiLevel gte 4 && buildApi gte 4>android:allowBackup="true"</#if>
        android:label="@string/app_name"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"        
        android:supportsRtl="true"
        android:name=".application.App"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest></#if>
</#macro>

<#macro androidConfig hasTests=false>

android {
    compileSdkVersion androidCompileSdkVersion
    buildToolsVersion androidBuildToolsVersion

    defaultConfig {
        applicationId "${packageName}"
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
