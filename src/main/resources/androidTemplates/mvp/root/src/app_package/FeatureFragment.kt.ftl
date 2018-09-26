package ${escapeKotlinIdentifiers(packageName)}

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.${fragment_layout}.*

import ${escapeKotlinIdentifiers(packageName)}.di.${name}Module
import ${escapeKotlinIdentifiers(packageName)}.di.Dagger${name}Component
import com.marcosholgado.core.di.CoreInjectHelper
import javax.inject.Inject


class ${name}Fragment : Fragment(), ${name}Contract.View {

    @Inject
    lateinit var presenter: ${name}Contract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.${fragment_layout}, container, false)


    override fun onAttach(context: Context) {
        Dagger${name}Component
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(activity!!.applicationContext))
            .${extractLetters(name?lower_case)}Module(${name}Module(this))
            .build()
            .inject(this)
        super.onAttach(context)
    }

}
