package com.example.mapper

interface Mapper<Data, Dto> {
    fun Dto.toData(): Data
    fun Data.toDto(): Dto

    val Byte?.asSafe get() = this ?: -1
    val Int?.asSafe get() = this ?: -1
    val Long?.asSafe get() = this ?: -1
    val Short?.asSafe get() = this ?: -1
    val Float?.asSafe get() = this ?: -1.0f
    val Double?.asSafe get() = this ?: -1.0
    val Boolean?.asSafe get() = this ?: false
    val String?.asSafe get() = orEmpty()

    companion object {
        inline infix fun <reified T, R> T?.safeMapOf(default: (T?) -> R): R {
            return default(this)
        }
    }
}
