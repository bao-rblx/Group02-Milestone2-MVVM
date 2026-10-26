package vn.edu.vlu.group02.shop.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.vlu.group02.shop.data.Category
import vn.edu.vlu.group02.shop.data.Product
import vn.edu.vlu.group02.shop.data.ShopRepository
import vn.edu.vlu.group02.shop.databinding.FragmentHomeBinding
import vn.edu.vlu.group02.shop.ui.adapter.ProductAdapter
import vn.edu.vlu.group02.shop.ui.detail.ProductDetailActivity

/**
 * Màn Home: danh sách sản phẩm + lọc theo category + tìm kiếm.
 * ViewBinding; observe(viewLifecycleOwner); _binding = null trong onDestroyView.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(
            onItemClick = { openDetail(it) },
            onAddClick = {
                ShopRepository.addToCart(it, 1)
                Toast.makeText(requireContext(), "Đã thêm: ${it.name}", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = adapter

        // Lọc theo chip
        binding.chipAll.setOnClickListener { viewModel.onCategorySelected(Category.ALL) }
        binding.chipPhone.setOnClickListener { viewModel.onCategorySelected(Category.PHONE) }
        binding.chipLaptop.setOnClickListener { viewModel.onCategorySelected(Category.LAPTOP) }
        binding.chipAccessory.setOnClickListener { viewModel.onCategorySelected(Category.ACCESSORY) }

        // Quan sát dữ liệu lifecycle-aware
        viewModel.products.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun openDetail(product: Product) {
        startActivity(
            Intent(requireContext(), ProductDetailActivity::class.java)
                .putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, product.id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvProducts.adapter = null
        _binding = null
    }
}
