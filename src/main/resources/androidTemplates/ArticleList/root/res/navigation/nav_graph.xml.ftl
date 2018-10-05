<?xml version="1.0" encoding="utf-8"?>
<navigation>
	<#if articleClickAction == "1">
    <fragment
    <#if isStartDestination>
		android:id="@+id/startDestinationAction"	   
	<#else>	
        android:id="@+id/${section}ArticleListAction"
    </#if>      
        android:name="com.marcosholgado.articlelist.ArticleListFragment"
        android:label="${section}" >

        <argument
            android:name="section"
            app:argType="string"
            android:defaultValue="${section}"
            />
    </fragment>
    <#else>
    <fragment
        <#if isStartDestination>
		android:id="@+id/startDestinationAction"	   
		<#else>	
        android:id="@+id/${section}ArticleListAction"
    	</#if>   
        android:name="${escapeKotlinIdentifiers(packageName)}.features.${section}.${underscoreToCamelCase(section)}Fragment"
        android:label="${section}">

        <argument
            android:name="section"
            app:argType="string"
            android:defaultValue="${section}"
            />

        <argument
            android:name="url"
            app:argType="string" />

        <action
            android:id="@+id/action_articles_${section}_to_reader"
            app:destination="@id/articleReaderActivity" />
        </fragment>   
    </#if>      
    <#if isStartDestination>
        <activity
        android:id="@+id/articleReaderActivity"
        android:name="com.marcosholgado.articlereader.ArticleReaderActivity"
        android:label="ArticleReaderActivity">

        <argument
            android:name="url"
            app:argType="string" />
        </activity>
    </#if> 
</navigation>