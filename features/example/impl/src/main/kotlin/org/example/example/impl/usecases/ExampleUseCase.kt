package org.example.example.impl.usecases

import com.example.usecases.SuspendUseCaseInput
import org.example.example.api.models.Example
import org.example.example.api.repositories.ExampleRepo

class ExampleUseCase(
    private val repo: ExampleRepo,
) : SuspendUseCaseInput<Example, Example> {

    override suspend fun invoke(value: Example): Example {
        return repo.updateData(value)
    }
}
