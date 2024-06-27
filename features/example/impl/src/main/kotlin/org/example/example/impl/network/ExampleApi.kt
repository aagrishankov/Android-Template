package org.example.example.impl.network

import kotlinx.coroutines.delay
import org.example.example.impl.network.models.ExampleDto
import org.example.example.impl.network.models.ExamplePageDto
import java.util.UUID

class ExampleApi {

    suspend fun getData(): List<ExampleDto> {

        delay(1000)

        return emptyList()
    }

    suspend fun postData(data: ExampleDto): ExampleDto {

        delay(1000)

        return data
    }

    private var _pagingRangeTest = 1..20

    suspend fun getPaging(query: Map<String, String>, page: Int, pageSize: Int): ExamplePageDto {

        delay(1000)

        println(page)
        println(pageSize)

        val items = createData(pageSize)

        return ExamplePageDto(page, pageSize, items = items)
    }
}

fun createData(items: Int): List<ExampleDto> {
    return List(items) {
        val id = UUID.randomUUID().toString()
        ExampleDto(id.hashCode(), id, true, null)
    }
}
