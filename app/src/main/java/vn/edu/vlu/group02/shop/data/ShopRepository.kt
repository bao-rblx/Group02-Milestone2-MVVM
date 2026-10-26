package vn.edu.vlu.group02.shop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Repository dùng chung cho giỏ hàng (in-memory).
 * Giỏ hàng là trạng thái toàn cục nên đặt ở đây, ViewModel chỉ quan sát.
 * Dùng LiveData để mọi màn hình cùng thấy cập nhật.
 */
object ShopRepository {

    private val _cart = MutableLiveData<List<CartItem>>(emptyList())
    val cart: LiveData<List<CartItem>> get() = _cart

    val total: Long
        get() = _cart.value?.sumOf { it.lineTotal } ?: 0L

    val itemCount: Int
        get() = _cart.value?.sumOf { it.quantity } ?: 0

    fun addToCart(product: Product, quantity: Int = 1) {
        if (quantity <= 0) return
        val current = _cart.value.orEmpty().toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val item = current[index]
            current[index] = item.copy(quantity = item.quantity + quantity)
        } else {
            current.add(CartItem(product, quantity))
        }
        _cart.value = current
    }

    fun remove(productId: Int) {
        _cart.value = _cart.value.orEmpty().filterNot { it.product.id == productId }
    }

    fun changeQuantity(productId: Int, newQuantity: Int) {
        if (newQuantity <= 0) {
            remove(productId); return
        }
        _cart.value = _cart.value.orEmpty().map {
            if (it.product.id == productId) it.copy(quantity = newQuantity) else it
        }
    }

    fun clear() {
        _cart.value = emptyList()
    }
}
