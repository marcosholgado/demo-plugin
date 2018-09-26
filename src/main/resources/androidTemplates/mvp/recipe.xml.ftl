<?xml version="1.0"?>
<recipe>

    <instantiate from="root/src/app_package/di/DaggerComponent.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/di/${name}Component.kt" />

    <instantiate from="root/src/app_package/di/DaggerModule.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/di/${name}Module.kt" />

    <instantiate from="root/src/app_package/model/Model.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/model/${name}.kt" />

    <instantiate from="root/src/app_package/FeatureContract.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/${name}Contract.kt" />

    <instantiate from="root/src/app_package/FeatureFragment.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/${name}Fragment.kt" />

    <instantiate from="root/src/app_package/FeaturePresenter.kt.ftl"
                to="${escapeXmlAttribute(srcOut)}/${name}Presenter.kt" />

    <instantiate from="root/res/layout/fragment_layout.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${fragment_layout}.xml" />

    <merge from="root/build.gradle.ftl"
             to="${escapeXmlAttribute(projectOut)}/build.gradle" />
</recipe>
