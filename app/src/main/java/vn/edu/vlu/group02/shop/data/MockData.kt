package vn.edu.vlu.group02.shop.data

import vn.edu.vlu.group02.shop.R

/**
 * Dữ liệu tĩnh mẫu.
 * Theo yêu cầu Milestone 2, dữ liệu tĩnh được đưa vào tầng ViewModel/Repository
 * thay vì hard-code trong Activity/Fragment/Adapter.
 */
object MockData {

    val products: List<Product> = listOf(
        Product(1, "Pixel 8 Pro 128GB", 18_990_000L,
            "Flagship Google, chip Tensor G3, camera 50MP, màn hình LTPO 6.7\".",
            Category.PHONE, "4.8 (256)", 12, R.drawable.ic_product_placeholder),
        Product(2, "Galaxy S24 Ultra", 26_490_000L,
            "Snapdragon 8 Gen 3, camera 200MP, bút S Pen tích hợp.",
            Category.PHONE, "4.9 (412)", 8, R.drawable.ic_product_placeholder),
        Product(3, "iPhone 15 Pro Max", 34_990_000L,
            "Chip A17 Pro, khung Titan, camera 48MP, USB-C.",
            Category.PHONE, "4.9 (980)", 5, R.drawable.ic_product_placeholder),
        Product(4, "Xiaomi 14", 15_990_000L,
            "Snapdragon 8 Gen 3, Leica optics, sạc nhanh 90W.",
            Category.PHONE, "4.7 (188)", 20, R.drawable.ic_product_placeholder),
        Product(5, "MacBook Air M3", 27_990_000L,
            "Chip Apple M3, 8GB RAM, 256GB SSD, mỏng nhẹ 1.24kg.",
            Category.LAPTOP, "4.8 (320)", 6, R.drawable.ic_product_placeholder),
        Product(6, "Dell XPS 13 Plus", 29_490_000L,
            "Intel Core i7 Gen 13, màn 13.4\" 3.5K OLED, bàn phím phẳng.",
            Category.LAPTOP, "4.6 (145)", 4, R.drawable.ic_product_placeholder),
        Product(7, "AirPods Pro 2", 5_990_000L,
            "Chống ồn chủ động, chip H2, hộp sạc USB-C.",
            Category.ACCESSORY, "4.9 (760)", 30, R.drawable.ic_product_placeholder),
        Product(8, "Sạc dự phòng Anker 20000mAh", 890_000L,
            "Sạc nhanh 65W, 2 cổng USB-C, hiển thị dung lượng.",
            Category.ACCESSORY, "4.5 (502)", 50, R.drawable.ic_product_placeholder)
    )

    val profile: UserProfile = UserProfile(
        name = "Lưu Gia Bảo",
        email = "2474802010028@vlu.edu.vn",
        addressCount = 2,
        orderCount = 5
    )

    fun byId(id: Int): Product? = products.firstOrNull { it.id == id }
}
