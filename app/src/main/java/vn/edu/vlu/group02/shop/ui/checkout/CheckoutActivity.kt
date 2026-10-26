package vn.edu.vlu.group02.shop.ui.checkout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.vlu.group02.shop.databinding.ActivityCheckoutBinding
import vn.edu.vlu.group02.shop.ui.adapter.OrderSummaryAdapter

/**
 * Màn Checkout: form người nhận (validate), tóm tắt đơn, phương thức thanh toán.
 * ViewModel trả cờ lỗi Boolean; Activity ánh xạ sang @string (giữ ViewModel sạch UI).
 * ViewBinding; observe(this); binding = null trong onDestroy.
 */
class CheckoutActivity : AppCompatActivity() {

    private var _binding: ActivityCheckoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CheckoutViewModel by viewModels()
    private val summaryAdapter = OrderSummaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(vn.edu.vlu.group02.shop.R.string.checkout_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvSummary.layoutManager = LinearLayoutManager(this)
        binding.rvSummary.adapter = summaryAdapter

        viewModel.cart.observe(this) { items ->
            summaryAdapter.submitList(items)
            binding.tvOrderTitle.text =
                getString(vn.edu.vlu.group02.shop.R.string.checkout_section_order, items.size)
        }
        viewModel.totalText.observe(this) { /* tổng hiển thị nếu bố cục mở rộng */ }
        viewModel.orderPlaced.observe(this) { placed ->
            if (placed) {
                Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show()
                viewModel.onOrderHandled()
                finish()
            }
        }

        binding.btnPlaceOrder.setOnClickListener { submit() }
    }

    private fun submit() {
        val name = binding.edtName.text?.toString().orEmpty()
        val phone = binding.edtPhone.text?.toString().orEmpty()
        val address = binding.edtAddress.text?.toString().orEmpty()

        val result = viewModel.validate(name, phone, address)
        binding.tilName.error = if (result.nameInvalid) getString(vn.edu.vlu.group02.shop.R.string.checkout_name_error) else null
        binding.tilPhone.error = if (result.phoneInvalid) getString(vn.edu.vlu.group02.shop.R.string.checkout_phone_error) else null
        binding.tilAddress.error = if (result.addressInvalid) getString(vn.edu.vlu.group02.shop.R.string.checkout_address_error) else null

        if (result.isValid) viewModel.placeOrder()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish(); return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvSummary.adapter = null
        _binding = null
    }
}
