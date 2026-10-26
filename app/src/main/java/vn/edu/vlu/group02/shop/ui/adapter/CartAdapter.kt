package vn.edu.vlu.group02.shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.edu.vlu.group02.shop.data.CartItem
import vn.edu.vlu.group02.shop.databinding.ItemCartBinding
import vn.edu.vlu.group02.shop.util.Money

/** Adapter giỏ hàng. ViewBinding, không findViewById. */
class CartAdapter(
    private val onDeleteClick: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.imgCart.setImageResource(item.product.imageRes)
            binding.tvCartName.text = item.product.name
            binding.tvCartPrice.text = Money.vnd(item.product.price)
            binding.tvQtyLabel.text = "Số lượng: ${item.quantity}"
            binding.btnDelete.setOnClickListener { onDeleteClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<CartItem>() {
            override fun areItemsTheSame(a: CartItem, b: CartItem) =
                a.product.id == b.product.id
            override fun areContentsTheSame(a: CartItem, b: CartItem) = a == b
        }
    }
}
