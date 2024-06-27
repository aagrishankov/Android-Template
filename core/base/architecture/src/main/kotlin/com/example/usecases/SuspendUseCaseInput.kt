package com.example.usecases

interface SuspendUseCaseInput<Input, Output> {
    suspend operator fun invoke(value: Input): Output
}
