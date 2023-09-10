package com.example.presentation.screens.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.Character

class DiffItemCallBack {
    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}