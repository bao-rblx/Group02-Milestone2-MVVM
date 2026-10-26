package vn.edu.vlu.group02.shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.edu.vlu.group02.shop.data.Product
import vn.edu.vlu.group02.shop.databinding.ItemProductBinding
import vn.edu.vlu.group02.shop.util.Money

/**
 * Adapter danh sách sản phẩm (Home).
 * Dùng ViewBinding — KHÔNG findViewById.
 */
class ProductAdapter(
    private val onItemClick: (Product) -> Unit,
    private val onAddClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.imgProduct.setImageResource(product.imageRes)
            binding.tvName.text = product.name
            binding.tvDescription.text = product.description
            binding.tvPrice.text = Money.vnd(product.price)
            binding.tvRating.text = product.rating
            binding.root.setOnClickListener { onItemClick(product) }
            binding.btnAdd.setOnClickListener { onAddClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }
}
