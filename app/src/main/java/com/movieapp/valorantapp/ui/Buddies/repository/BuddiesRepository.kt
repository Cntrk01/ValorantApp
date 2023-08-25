package com.movieapp.valorantapp.ui.Buddies.repository

import com.movieapp.valorantapp.service.ValorantAPI
import javax.inject.Inject

class BuddiesRepository @Inject constructor(private val api: ValorantAPI) {
    suspend fun getBuddies() = api.buddies()
}