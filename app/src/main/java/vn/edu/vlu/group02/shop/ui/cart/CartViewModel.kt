package vn.edu.vlu.group02.shop.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import vn.edu.vlu.group02.shop.data.CartItem
import vn.edu.vlu.group02.shop.data.ShopRepository
import vn.edu.vlu.group02.shop.util.Money

/**
 * ViewModel cho màn Cart. Quan sát giỏ hàng dùng chung từ ShopRepository.
 * Không tham chiếu View/Activity/Fragment/Context.
 */
class CartViewModel : ViewModel() {

    val cart: LiveData<List<CartItem>> = ShopRepository.cart

    private val _totalText = MediatorLiveData<String>()
    val totalText: LiveData<String> = _totalText

    init {
        _totalText.addSource(ShopRepository.cart) { items ->
            val total = items.sumOf { it.lineTotal }
            _totalText.value = Money.vnd(total)
        }
    }

    fun removeItem(productId: Int) = ShopRepository.remove(productId)

    fun changeQuantity(productId: Int, quantity: Int) =
        ShopRepository.changeQuantity(productId, quantity)
}
