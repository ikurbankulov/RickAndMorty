package com.example.data.network.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.models.CharacterDto
import com.example.data.network.source.ApiService

class CharacterPagingSource(private val apiService: ApiService) : PagingSource<Int, CharacterDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val page = params.key ?: 1
            val response = apiService.loadCharacters(page)
            val data = response.result
            val prevPage = if (page == 1) null else page - 1
            val nextPage = if (data.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            Log.e("CharacterPagingSource", e.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return null
    }
}
