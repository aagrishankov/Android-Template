package org.example.storage

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    val isAuth: Flow<Boolean>
    fun getToken(): String
    fun setToken(token: String)
}
