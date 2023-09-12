package com.example.presentation.screens.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.models.Character
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.presentation.general.diff_callback.DiffItemCallBack
import com.example.rickandmorty.databinding.CharacterItemBinding

class FavouriteAdapter(var onItemClickListener: ((Character) -> Unit)? = null) :
    ListAdapter<Character, FavouriteViewHolder>(DiffItemCallBack.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
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