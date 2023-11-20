package my.demo.myapplication.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import my.demo.myapplication.domain.model.CryptoModel
import my.demo.myapplication.databinding.ItemCryptoBinding

/**
 * Adapter class for displaying a list of CryptoModel items in a RecyclerView.
 */
class CryptoAdapter : ListAdapter<CryptoModel, CryptoAdapter.ItemViewHolder>(DiffCallback()) {

    // Create and return a new ItemViewHolder when needed.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Bind data to the ViewHolder at the given position.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder class for holding views associated with each item in the RecyclerView.
    inner class ItemViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind data to the ViewHolder's views.
        fun bind(itemData: CryptoModel) = binding.apply {
            model = itemData
            executePendingBindings()
        }
    }

    // DiffCallback class for calculating the difference between two lists of CryptoModel items.
    class DiffCallback : DiffUtil.ItemCallback<CryptoModel>() {

        // Check if the items have the same Price.
        override fun areItemsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean =
            oldItem.price == newItem.price

        // Check if the contents of the items are the same.
        override fun areContentsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean =
            oldItem == newItem
    }
}
