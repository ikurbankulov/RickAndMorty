package com.example.presentation.general.state_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemLoadStateBinding


class DefaultLoadStateAdapter : LoadStateAdapter<DefaultLoadStateAdapter.LoadViewHolder>() {

    class LoadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_load_state,
            parent,
            false
        )
        return LoadViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

//    override fun getStateViewType(loadState: LoadState) = when (loadState) {
//        is LoadState.Loading -> LOADING
//        is LoadState.Error -> ERROR
//        is LoadState.NotLoading -> error("not supported")
//    }
//
//    private companion object {
//        const val LOADING = 1
//        const val ERROR = 2
//    }
//
//    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
//        holder.bind(loadState)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        loadState: LoadState
//    ): LoadStateViewHolder {
//        return LoadStateViewHolder.create(parent)
//    }
//}
//
//class LoadStateViewHolder internal constructor(
//    private val binding: ItemLoadStateBinding,
//) : RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(loadState: LoadState) {
//        binding.progressBar.isVisible = loadState is LoadState.Loading
//    }
//
//    companion object {
//        fun create(parent: ViewGroup): LoadStateViewHolder {
//            val binding = ItemLoadStateBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//            return LoadStateViewHolder(binding)
//        }
//    }
//}


