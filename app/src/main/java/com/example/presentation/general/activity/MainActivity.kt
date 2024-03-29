package com.example.presentation.general.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

        private var _binding: ActivityMainBinding? = null
        private val binding: ActivityMainBinding
                get() = _binding ?: throw RuntimeException(BINDING_NULL_MESSAGE)

        private val memoryLeakExample = MemoryLeakExample()
        override fun onDestroy() {
                super.onDestroy()
                _binding = null
        }
        override fun onCreate(savedInstanceState: Bundle?) {



                super.onCreate(savedInstanceState)
                _binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

               // memoryLeakExample.simulateMemoryLeak()
        }

        companion object {
                private const val BINDING_NULL_MESSAGE = "ActivityMainBinding is null"
        }
}
