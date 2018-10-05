dependencies {
    implementation project(path: ':articlelist')
    <#if !isStartDestination>
		implementation project(path: ':articlereader')	
	</#if>
}
