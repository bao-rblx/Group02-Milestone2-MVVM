package vn.edu.vlu.group02.shop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.edu.vlu.group02.shop.data.Category
import vn.edu.vlu.group02.shop.data.MockData
import vn.edu.vlu.group02.shop.data.Product

/**
 * ViewModel cho màn Home. Giữ danh sách sản phẩm + trạng thái lọc/tìm kiếm.
 * Không tham chiếu View/Activity/Fragment/Context.
 */
class HomeViewModel : ViewModel() {

    private val all: List<Product> = MockData.products

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    private var category: Category = Category.ALL
    private var query: String = ""

    init { applyFilter() }

    fun onCategorySelected(newCategory: Category) {
        category = newCategory
        applyFilter()
    }

    fun onSearchQueryChanged(newQuery: String) {
        query = newQuery.trim()
        applyFilter()
    }

    private fun applyFilter() {
        _products.value = all.filter { product ->
            val matchesCategory = category == Category.ALL || product.category == category
            val matchesQuery = query.isBlank() || product.name.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }
}
