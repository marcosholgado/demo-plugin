package ${escapeKotlinIdentifiers(packageName)}.features.${section}

import androidx.navigation.fragment.findNavController
import com.marcosholgado.articlelist.ArticleListFragment
import ${escapeKotlinIdentifiers(packageName)}.features.${section}.${underscoreToCamelCase(section)}FragmentDirections.action_articles_${section}_to_reader

class ${underscoreToCamelCase(section)}Fragment: ArticleListFragment() {

    override fun onItemSelected(url: String) {
        val navDirections = action_articles_${section}_to_reader(url)
        findNavController().navigate(navDirections)
    }
}