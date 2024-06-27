package org.example.example.impl.paging

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.example.mapper.Mapper
import org.example.example.api.models.Example
import org.example.example.impl.network.ExampleApi
import org.example.example.impl.network.models.ExampleDto

class ExamplePageSourceFactory(
    private val api: ExampleApi,
    private val mapper: Mapper<Example, ExampleDto>
) {
    operator fun invoke(query: Map<String, String>): ExamplePageSource {
        return ExamplePageSource(query, api, mapper)
    }
}

class ExamplePageSource(
    private val query: Map<String, String>,
    private val api: ExampleApi,
    mapper: Mapper<Example, ExampleDto>
) : PagingSource<Int, Example>(),
    Mapper<Example, ExampleDto> by mapper {

    override fun getRefreshKey(state: PagingState<Int, Example>): Int? {
        val pos = state.anchorPosition ?: return null

        val page = state.closestPageToPosition(pos) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Example> {

        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val response = api
                .getPaging(query = query, page = page, pageSize = pageSize)

            val data = response
                .items
                .orEmpty()
                .map { it.toData() }

            println("data size = ${data.size}")

            val nextKey =
                if (data.size < pageSize) null
                else page + 1

            println("nextKey = $nextKey")

            val prevKey =
                if (page == 1) null
                else page - 1

            println("prevKey = $prevKey")

            LoadResult.Page(data = data, nextKey = nextKey, prevKey = prevKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
