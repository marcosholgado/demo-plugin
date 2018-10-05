<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${packageName}">

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<#if !isStartDestination>
	<application>
		<activity android:name="com.marcosholgado.articlereader.ArticleReaderActivity" />
	</application>
</#if>
</manifest>