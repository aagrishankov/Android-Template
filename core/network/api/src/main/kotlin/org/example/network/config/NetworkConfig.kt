package org.example.network.config

import org.example.env.Environment

@Suppress("ConstPropertyName")
object NetworkConfig {
    const val isDebug: Boolean = Environment.IS_DEV_STAGE
    const val DEFAULT_BASE_URL: String = Environment.DEFAULT_BASE_URL
}
