package vn.edu.vlu.group02.shop.data

import androidx.annotation.DrawableRes

/**
 * Một sản phẩm trong shop.
 * Model thuần — KHÔNG phụ thuộc View/Activity/Fragment.
 */
enum class Category {
    ALL, PHONE, LAPTOP, ACCESSORY
}

data class Product(
    val id: Int,
    val name: String,
    val price: Long,
    val description: String,
    val category: Category,
    val rating: String,
    val stock: Int,
    @DrawableRes val imageRes: Int
)
