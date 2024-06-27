package org.example.example.api.models

data class Example(
    val id: Int,
    val name: String,
    val isOk: Boolean,
    val data: InnerData
) {

    companion object {
        val EMPTY = Example(
            id = 1,
            name = "",
            isOk = true,
            data = InnerData(data = 1)
        )
    }

    data class InnerData(
        val data: Int?,
    )
}
