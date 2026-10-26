package vn.edu.vlu.group02.shop.util

import java.text.NumberFormat
import java.util.Locale

/** Định dạng tiền Việt Nam: 18.990.000 ₫ */
object Money {
    private val fmt: NumberFormat = NumberFormat.getIntegerInstance(Locale("vi", "VN"))

    fun vnd(amount: Long): String = fmt.format(amount) + " ₫"
}
