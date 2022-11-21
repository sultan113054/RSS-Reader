package com.application.rssreader.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.rssreader.core.extension.loadFromUrl
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.databinding.RssFeedsListItemBinding


class RSSFeedsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<RSSFeedModel>() {
        override fun areItemsTheSame(oldItem: RSSFeedModel, newItem: RSSFeedModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RSSFeedModel, newItem: RSSFeedModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RssFeedsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RSSFeedsItemViewHolder(binding)

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        (holder as RSSFeedsItemViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RSSFeedsItemViewHolder(
        private var binding: RssFeedsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RSSFeedModel) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.ivThumbnail.loadFromUrl(
                item.thumbnail
            )

        }
    }


    private var onItemClickListener: ((RSSFeedModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (RSSFeedModel) -> Unit) {
        onItemClickListener = listener
    }
}









