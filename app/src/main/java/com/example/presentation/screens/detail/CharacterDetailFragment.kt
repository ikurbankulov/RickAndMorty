package com.example.presentation.screens.detail

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.App
import com.example.presentation.general.viewmodel_factory.ViewModelFactory
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {

    private lateinit var viewModel: CharacterDetailViewModel
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

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
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacterDetailViewModel::class.java]

        val characterId = requireArguments().getInt(EXTRA_ID)
        viewModel.loadCharacter(characterId)

        setupUI()
        checkIsCharacterInFavourite(characterId)

        binding.favouriteImageView.setOnClickListener {
            toggleFavoriteState()
        }
    }

    private fun setupUI() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            Glide.with(this)
                .load(character.image)
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewCharacter)

            with(binding) {
                textViewCharacterName.text = character.name
                textViewStatus.text = character.status
                textViewGender.text = character.gender
                textViewSpecies.text = character.species
                textViewGenderLocation.text = character.location.name
            }
        }
    }

    private fun checkIsCharacterInFavourite(characterId: Int) {
        viewModel.isCharacterInFavorites(characterId).observe(viewLifecycleOwner) { isFav ->
            isFavorite = isFav
            updateFavoriteIcon()
        }
    }


    private fun toggleFavoriteState() {
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    companion object {
        private const val EXTRA_ID = "extra_id"

        fun newInstance(id: Int): CharacterDetailFragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_ID, id)
                }
            }
        }
    }
}

