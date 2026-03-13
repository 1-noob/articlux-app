package com.mecharium.articlux_1.data.model


data class ProceedResponse(
    val staus: String,
    val message: String,
    val stats: SyncStats
)

data class SyncStats(
    val inserted: Int,
    val skipped: Int,
    val errors: Int
)