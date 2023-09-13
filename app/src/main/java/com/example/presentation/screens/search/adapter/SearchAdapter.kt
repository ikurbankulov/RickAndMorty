package com.example.presentation.screens.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.models.Character
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.presentation.general.diff_callback.DiffItemCallBack
import com.example.presentation.screens.search.adapter.SearchViewHolder
import com.example.rickandmorty.databinding.CharacterItemBinding

class SearchAdapter(var onItemClickListener: ((Character) -> Unit)? = null) :
    ListAdapter<Character, SearchViewHolder>(DiffItemCallBack.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val character = getItem(position)
        Glide.with(holder.itemView)
            .load(character.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(300, 300)
            .into(holder.binding.imageViewCharacter)

        with(holder.binding) {
            textViewCharacterName.text = character.name
            textViewStatus.text = character.status
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(character)
        }
    }
}