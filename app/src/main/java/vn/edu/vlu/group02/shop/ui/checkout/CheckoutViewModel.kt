package vn.edu.vlu.group02.shop.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.edu.vlu.group02.shop.data.CartItem
import vn.edu.vlu.group02.shop.data.ShopRepository
import vn.edu.vlu.group02.shop.util.Money

/** Kết quả kiểm tra một lần: cờ lỗi theo từng trường + hợp lệ tổng thể. */
data class ValidationResult(
    val nameInvalid: Boolean,
    val phoneInvalid: Boolean,
    val addressInvalid: Boolean
) {
    val isValid: Boolean get() = !nameInvalid && !phoneInvalid && !addressInvalid
}

/**
 * ViewModel cho màn Checkout.
 * Trả về cờ lỗi dạng Boolean (KHÔNG trả chuỗi/không giữ Context);
 * Activity tự ánh xạ cờ -> @string để hiển thị. Không tham chiếu View/Context.
 */
class CheckoutViewModel : ViewModel() {

    val cart: LiveData<List<CartItem>> = ShopRepository.cart

    private val _totalText = MediatorLiveData<String>()
    val totalText: LiveData<String> = _totalText

    private val _orderPlaced = MutableLiveData(false)
    val orderPlaced: LiveData<Boolean> = _orderPlaced

    init {
        _totalText.addSource(ShopRepository.cart) { items ->
            _totalText.value = Money.vnd(items.sumOf { it.lineTotal })
        }
    }

    fun validate(name: String, phone: String, address: String): ValidationResult {
        val nameInvalid = name.trim().length < 3
        val digits = phone.filter { it.isDigit() }
        val phoneInvalid = digits.length !in 9..11
        val addressInvalid = address.trim().length < 5
        return ValidationResult(nameInvalid, phoneInvalid, addressInvalid)
    }

    /** Gọi sau khi validate().isValid == true. Xoá giỏ, báo đặt hàng thành công. */
    fun placeOrder() {
        ShopRepository.clear()
        _orderPlaced.value = true
    }

    fun onOrderHandled() { _orderPlaced.value = false }
}
