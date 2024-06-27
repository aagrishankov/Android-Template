package org.example.env

import nota_template.core.environment.BuildConfig

object Environment {

    const val DEFAULT_BASE_URL = BuildConfig.DEFAULT_BASE_URL
    const val DEFAULT_BASE_WEB_URL = BuildConfig.DEFAULT_BASE_WEB_URL

    const val IS_DEV_STAGE = BuildConfig.IS_DEV_STAGE

    val stages = BuildConfig.stages
    val webStages = BuildConfig.webStages
}
