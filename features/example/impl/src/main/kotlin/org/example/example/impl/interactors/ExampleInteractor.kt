package org.example.example.impl.interactors

import org.example.example.api.interactors.ExampleInteractor
import org.example.example.api.models.Example
import org.example.example.impl.usecases.ExampleUseCase

class ExampleInteractorImpl(
    private val exampleUseCase: ExampleUseCase
) : ExampleInteractor {

    override suspend fun update(data: Example): Example {
        throw NullPointerException()
        return exampleUseCase(data)
    }
}
