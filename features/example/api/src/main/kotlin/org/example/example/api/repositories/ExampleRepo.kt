package org.example.example.api.repositories

import org.example.example.api.models.Example

interface ExampleRepo {
    suspend fun getData(): List<Example>
    suspend fun updateData(data: Example): Example
}
