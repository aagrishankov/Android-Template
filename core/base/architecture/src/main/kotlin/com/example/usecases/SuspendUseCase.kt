package com.example.usecases

interface SuspendUseCase<Output> {
    suspend operator fun invoke(): Output
}
