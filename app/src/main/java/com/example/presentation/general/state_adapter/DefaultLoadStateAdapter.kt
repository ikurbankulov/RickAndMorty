package com.example.presentation.general.state_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemLoadStateBinding


class DefaultLoadStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {

    private var loadStateListener: ((LoadState) -> Unit)? = null

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.Loading -> LOADING
        is LoadState.Error -> ERROR
        is LoadState.NotLoading -> error("not supported")
    }

    fun setLoadStateListener(listener: ((LoadState) -> Unit)?) {
        loadStateListener = listener
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        loadStateListener?.invoke(loadState)

        if (loadState is LoadState.Error) {
            val errorMessage = holder.itemView.context.getString(R.string.error_message)
            Toast.makeText(holder.itemView.context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent)
    }

    private companion object {
        const val LOADING = 1
        const val ERROR = 2
    }
}

class LoadStateViewHolder internal constructor(
    private val binding: ItemLoadStateBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
    }



    companion object {
        fun create(parent: ViewGroup): LoadStateViewHolder {
            val binding = ItemLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return LoadStateViewHolder(binding)
        }
    }
}



