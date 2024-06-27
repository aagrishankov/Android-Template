package org.example.example.impl.mappers

import com.example.mapper.Mapper
import com.example.mapper.Mapper.Companion.safeMapOf
import org.example.example.api.models.Example
import org.example.example.impl.network.models.ExampleDto

class ExampleMapper : Mapper<Example, ExampleDto> {

    override fun ExampleDto.toData(): Example {
        return Example(
            id = id.asSafe,
            name = name.asSafe,
            isOk = isOk.asSafe,
            data = data safeMapOf {
                Example.InnerData(
                    data = it?.data.asSafe,
                )
            }
        )
    }

    override fun Example.toDto(): ExampleDto {
        return ExampleDto(
            id = id,
            name = name,
            isOk = isOk,
            data = ExampleDto.InnerData(
                data = data.data,
            )
        )
    }
}
