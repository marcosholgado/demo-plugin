<?xml version="1.0"?>
<recipe>
    <merge from="root/res/menu/navigation.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/menu/navigation.xml" />

    <merge from="root/res/navigation/nav_graph.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/navigation/nav_graph.xml" />

    <merge from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(projectOut)}/build.gradle" />
   	
</recipe>
