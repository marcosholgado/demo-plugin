<?xml version="1.0"?>
<recipe>

    <instantiate from="root/src/app_package/DaggerComponent.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/${name}Component.kt" />

    <instantiate from="root/src/app_package/DaggerModule.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/${name}Module.kt" />

</recipe>
