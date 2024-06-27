package org.example.example.impl.repositories

import com.example.mapper.Mapper
import org.example.example.api.models.Example
import org.example.example.api.repositories.ExampleRepo
import org.example.example.impl.network.ExampleApi
import org.example.example.impl.network.models.ExampleDto

class ExampleRepoImpl(
    mapper: Mapper<Example, ExampleDto>,
    private val api: ExampleApi,
) : ExampleRepo,
    Mapper<Example, ExampleDto> by mapper {

    override suspend fun getData(): List<Example> {
        return api.getData().map { it.toData() }
    }

    override suspend fun updateData(data: Example): Example {
        return api.postData(data.toDto()).toData()
    }
}
