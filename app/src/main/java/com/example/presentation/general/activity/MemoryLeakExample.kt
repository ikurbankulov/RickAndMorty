package com.example.presentation.general.activity

class MemoryLeakExample {

        private val leakedList = mutableListOf<String>()

        fun simulateMemoryLeak() {
                leakedList.addAll(generateData())
        }

        private fun generateData(): List<String> {
                val data = mutableListOf<String>()
                repeat(1000_000) {
                        data.add("Data $it")
                }
                return data
        }
}