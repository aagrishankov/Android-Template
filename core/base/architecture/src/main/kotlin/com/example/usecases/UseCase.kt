package com.example.usecases

interface UseCase<Output> {
    operator fun invoke(): Output
}
