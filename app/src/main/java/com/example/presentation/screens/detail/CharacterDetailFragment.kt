package com.example.presentation.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException(BINDING_NULL_MESSAGE)

    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        viewModel = ViewModelProvider(this)[CharacterDetailViewModel::class.java]
        val characterId = requireArguments().getInt(EXTRA_ID)
        characterId.let { viewModel.loadCharacter(it) }

        viewModel.character.observe(viewLifecycleOwner) {
            Glide.with(this)
                .load(it.image)
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewCharacter)

            with(binding) {
                textViewCharacterName.text = it.name
                textViewStatus.text = it.status
                textViewGender.text = it.gender
                textViewSpecies.text = it.species
                textViewGenderLocation.text = it.location.name
            }
        }

        viewModel.isCharacterInFavorites(characterId).observe(viewLifecycleOwner) { isFav ->
            isFavorite = isFav
            updateFavoriteIcon()
        }


        binding.favouriteImageView.setOnClickListener {
            val character = viewModel.character.value
            character?.let {
                if (isFavorite) {
                    viewModel.removeFromFavourites(it)
                } else {
                    viewModel.addToFavourites(it)
                }
                isFavorite = !isFavorite
                updateFavoriteIcon()
            }
        }
    }

    private fun updateFavoriteIcon() {
        val favoriteIconResId = if (isFavorite) {
            android.R.drawable.btn_star_big_on
        } else {
            android.R.drawable.btn_star_big_off
        }
        binding.favouriteImageView.setImageResource(favoriteIconResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_ID = "extra_id"
        private const val TAG = "CharacterDetailFragment"
        private const val BINDING_NULL_MESSAGE = "FragmentCharacterDetailBinding is null"

        fun newInstance(id: Int): Fragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_ID, id)
                }
            }
        }
    }
}
