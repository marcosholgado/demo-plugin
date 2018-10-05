<?xml version="1.0"?>
<recipe>
    <merge from="root/res/menu/navigation.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/menu/navigation.xml" />

    <merge from="root/res/navigation/nav_graph.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/navigation/nav_graph.xml" />

    <merge from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(projectOut)}/build.gradle" />

	<merge from="root/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

<#if !isStartDestination>
	<mkdir at="${escapeXmlAttribute(srcOut)}/features/${section}" />

    <instantiate from="root/src/app_package/SectionFragment.kt.ftl" 
    	to="${escapeXmlAttribute(srcOut)}/features/${section}/${underscoreToCamelCase(section)}Fragment.kt" /> 
</#if>    	
</recipe>
