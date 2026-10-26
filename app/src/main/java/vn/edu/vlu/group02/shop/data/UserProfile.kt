package vn.edu.vlu.group02.shop.data

/** Thông tin người dùng hiển thị ở màn Profile. */
data class UserProfile(
    val name: String,
    val email: String,
    val addressCount: Int,
    val orderCount: Int
)
