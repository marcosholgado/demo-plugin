package ${escapeKotlinIdentifiers(packageName)}

import javax.inject.Inject

class ${name}Presenter @Inject constructor(
    private val view: ${name}Contract.View
) : ${name}Contract.Presenter {

}