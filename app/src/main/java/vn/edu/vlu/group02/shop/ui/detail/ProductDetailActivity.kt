package vn.edu.vlu.group02.shop.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import vn.edu.vlu.group02.shop.data.ShopRepository
import vn.edu.vlu.group02.shop.databinding.ActivityProductDetailBinding
import vn.edu.vlu.group02.shop.util.Money

/**
 * Màn Product Detail. Nhận productId qua Intent, hiển thị + thêm vào giỏ.
 * ViewBinding; observe(this) lifecycle-aware; binding = null trong onDestroy.
 */
class ProductDetailActivity : AppCompatActivity() {

    private var _binding: ActivityProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.Factory(intent.getIntExtra(EXTRA_PRODUCT_ID, -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.product.observe(this) { product ->
            product ?: return@observe
            binding.imgDetail.setImageResource(product.imageRes)
            binding.tvDetailName.text = product.name
            binding.tvDetailPrice.text = Money.vnd(product.price)
            binding.tvDetailRating.text = product.rating
            binding.tvStock.text = "Còn ${product.stock} sản phẩm"
        }

        viewModel.quantity.observe(this) { qty ->
            binding.tvQty.text = "Số lượng: $qty"
        }

        viewModel.addedEvent.observe(this) { added ->
            if (added) {
                Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show()
                viewModel.onAddedHandled()
            }
        }

        binding.btnAddCart.setOnClickListener { viewModel.addToCart() }
        binding.btnBuyNow.setOnClickListener {
            val product = viewModel.product.value ?: return@setOnClickListener
            ShopRepository.addToCart(product, viewModel.quantity.value ?: 1)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish(); return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_PRODUCT_ID = "extra_product_id"
    }
}
