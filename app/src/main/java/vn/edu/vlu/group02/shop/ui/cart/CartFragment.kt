package vn.edu.vlu.group02.shop.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.vlu.group02.shop.databinding.FragmentCartBinding
import vn.edu.vlu.group02.shop.ui.adapter.CartAdapter
import vn.edu.vlu.group02.shop.ui.checkout.CheckoutActivity

/**
 * Màn Cart: danh sách giỏ + tổng tiền, xoá dòng, sang Checkout.
 * ViewBinding; observe(viewLifecycleOwner); _binding = null trong onDestroyView.
 */
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by activityViewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(onDeleteClick = { viewModel.removeItem(it.product.id) })
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(requireContext(), CheckoutActivity::class.java))
        }

        viewModel.cart.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
        viewModel.totalText.observe(viewLifecycleOwner) { total ->
            binding.tvTotalValue.text = total
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvCart.adapter = null
        _binding = null
    }
}
