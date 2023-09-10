package com.example.presentation.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.list.adapter.RecyclerViewAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupClickListener()
    }

    private fun init() {
        adapter = RecyclerViewAdapter()
        binding.recyclerViewCharacters.adapter = adapter
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupClickListener() {
        adapter.onItemClickListener = { character ->
            Toast.makeText(requireContext(), character.name, Toast.LENGTH_SHORT).show()
            val characterDetailFragment = CharacterDetailFragment.newInstance(character.id)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, characterDetailFragment)
                .addToBackStack(null)
                .commit()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CharacterListFragment()
    }
}
