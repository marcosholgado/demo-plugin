package ${escapeKotlinIdentifiers(packageName)}.features.${section}

import androidx.navigation.fragment.findNavController
import com.marcosholgado.articlelist.ArticleListFragment
import ${escapeKotlinIdentifiers(packageName)}.features.${section}.${underscoreToCamelCase(section)}FragmentDirections.actionArticles${underscoreToCamelCase(section)}ToReader

class ${underscoreToCamelCase(section)}Fragment: ArticleListFragment() {

    override fun onItemSelected(url: String) {
        val navDirections = actionArticles${underscoreToCamelCase(section)}ToReader(url)
        findNavController().navigate(navDirections)
    }
}