package ${escapeKotlinIdentifiers(packageName)}.di

import com.marcosholgado.core.di.CoreComponent
import com.marcosholgado.core.di.FeatureScope
import ${escapeKotlinIdentifiers(packageName)}.${name}Fragment

import dagger.Component

@Component(modules = [${name}Module::class], dependencies = [CoreComponent::class])
@FeatureScope
interface ${name}Component {
    fun inject(fragment: ${name}Fragment)
}