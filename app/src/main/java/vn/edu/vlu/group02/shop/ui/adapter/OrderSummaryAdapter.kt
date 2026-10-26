package vn.edu.vlu.group02.shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.edu.vlu.group02.shop.data.CartItem
import vn.edu.vlu.group02.shop.databinding.ItemOrderSummaryBinding
import vn.edu.vlu.group02.shop.util.Money

/** Adapter các dòng tóm tắt đơn ở màn Checkout. ViewBinding, không findViewById. */
class OrderSummaryAdapter : ListAdapter<CartItem, OrderSummaryAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemOrderSummaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.tvSummaryName.text = "${item.product.name} × ${item.quantity}"
            binding.tvSummaryPrice.text = Money.vnd(item.lineTotal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemOrderSummaryBinding.inflate(
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
