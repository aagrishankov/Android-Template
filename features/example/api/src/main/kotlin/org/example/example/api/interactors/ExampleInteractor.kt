package org.example.example.api.interactors

import org.example.example.api.models.Example

interface ExampleInteractor {
    suspend fun update(data: Example): Example
}
