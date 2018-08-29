package ${packageName}

import com.marcosholgado.core.di.CoreComponent
import com.marcosholgado.core.di.FeatureScope
import dagger.Component

@Component(modules = [${name}Module::class], dependencies = [CoreComponent::class])
@FeatureScope
interface ${name}Component {

}