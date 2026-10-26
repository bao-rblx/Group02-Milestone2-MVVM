package vn.edu.vlu.group02.shop.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vn.edu.vlu.group02.shop.data.MockData
import vn.edu.vlu.group02.shop.data.Product
import vn.edu.vlu.group02.shop.data.ShopRepository

/**
 * ViewModel cho màn Product Detail. Nhận productId qua Factory.
 * Không tham chiếu View/Activity/Fragment/Context.
 */
class DetailViewModel(productId: Int) : ViewModel() {

    private val _product = MutableLiveData<Product?>()
    val product: LiveData<Product?> = _product

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity

    /** Sự kiện một lần: đã thêm vào giỏ (để Activity hiện Snackbar/toast). */
    private val _addedEvent = MutableLiveData(false)
    val addedEvent: LiveData<Boolean> = _addedEvent

    init { _product.value = MockData.byId(productId) }

    fun increaseQuantity() {
        _quantity.value = (_quantity.value ?: 1) + 1
    }

    fun decreaseQuantity() {
        val q = _quantity.value ?: 1
        if (q > 1) _quantity.value = q - 1
    }

    fun addToCart() {
        val product = _product.value ?: return
        ShopRepository.addToCart(product, _quantity.value ?: 1)
        _addedEvent.value = true
    }

    fun onAddedHandled() { _addedEvent.value = false }

    class Factory(private val productId: Int) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(productId) as T
    }
}
