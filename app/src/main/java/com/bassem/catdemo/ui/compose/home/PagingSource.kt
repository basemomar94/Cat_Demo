package com.bassem.catdemo.ui.compose.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PagingSource(
    private val api: CatService,
    private val dao: CatsDao
) : PagingSource<Int, BreedItem>() {

    private val logger = Logger(this::class.java.simpleName)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreedItem> {

        val page = params.key ?: 0
        return try {
            val response = api.getCatsBreeds(limit = params.loadSize, page = page)
            withContext(Dispatchers.IO) {
                dao.deleteAllBreeds()
                dao.insertAllBreeds(response)
            }
            logger.i("page is $page  response is ${response.size}")
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            logger.e("load is fail ${e.message}")
            val localBreeds = withContext(Dispatchers.IO) { dao.getAllBreeds() }
            if (localBreeds.isEmpty()) {
                LoadResult.Error(e)

            } else {
                LoadResult.Page(
                    data = localBreeds,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (localBreeds.isEmpty()) null else page + 1
                )
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BreedItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}

