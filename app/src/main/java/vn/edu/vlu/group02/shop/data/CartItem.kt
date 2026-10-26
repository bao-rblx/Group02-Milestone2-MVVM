package vn.edu.vlu.group02.shop.data

/** Một dòng trong giỏ hàng: sản phẩm + số lượng. */
data class CartItem(
    val product: Product,
    val quantity: Int
) {
    val lineTotal: Long get() = product.price * quantity
}
