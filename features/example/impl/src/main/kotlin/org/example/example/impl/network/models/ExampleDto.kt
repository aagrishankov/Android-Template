package org.example.example.impl.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ExampleDto(
    val id: Int?,
    val name: String?,
    val isOk: Boolean?,
    val data: InnerData?,
) {
    @Serializable
    data class InnerData(
        val data: Int?,
    )
}

@Serializable
data class ExamplePageDto(
    val page: Int?,
    val maxPages: Int?,
    val items: List<ExampleDto>?,
)
