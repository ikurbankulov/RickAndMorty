package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.models.CharacterEntity
import com.example.data.network.ApiService

class PagingSource(
    private val api: ApiService
) : PagingSource<Int, CharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        return try {
            val page = params.key ?: 1
            val response = api.loadCharacters(page)
            val data = response.result
            val prevPage = if (page == 1) null else page - 1
            val nextPage = if (data.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return null
    }
}
