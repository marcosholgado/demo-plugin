package ${escapeKotlinIdentifiers(packageName)}.di

import ${escapeKotlinIdentifiers(packageName)}.${name}Contract
import ${escapeKotlinIdentifiers(packageName)}.${name}Presenter
import dagger.Module
import dagger.Provides

@Module
class ${name}Module(val view: ${name}Contract.View) {

    @Provides
    fun providesPresenter(presenter: ${name}Presenter): ${name}Contract.Presenter = presenter

    @Provides
    fun providesView() : ${name}Contract.View = view
}