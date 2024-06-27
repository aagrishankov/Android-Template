package com.example.usecases

interface UseCaseInput<Input, Output> {
    operator fun invoke(value: Input): Output
}
