 <menu xmlns:android="http://schemas.android.com/apk/res/android">
 	<item
 	<#if isStartDestination>
		android:id="@+id/startDestinationAction"	   
	<#else>	
        android:id="@+id/${section}ArticleListAction"
    </#if>          
        android:icon="@drawable/${section}"
        android:title="${underscoreToCamelCase(section)}" />
</menu>        	