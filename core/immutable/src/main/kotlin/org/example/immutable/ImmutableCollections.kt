package org.example.immutable

import androidx.compose.runtime.Immutable

@[Immutable JvmInline]
value class ImmutableList<T>(private val data: List<T>) : List<T> by data

@[Immutable JvmInline]
value class ImmutableSet<T>(private val data: Set<T>) : Set<T> by data

@[Immutable JvmInline]
value class ImmutableMap<K, V>(private val data: Map<K, V>) : Map<K, V> by data
