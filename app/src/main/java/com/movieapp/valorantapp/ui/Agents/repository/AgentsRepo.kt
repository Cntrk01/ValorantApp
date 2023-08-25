package com.movieapp.valorantapp.ui.Agents.repository

import com.movieapp.valorantapp.service.ValorantAPI
import javax.inject.Inject

class AgentsRepo @Inject constructor(private val api: ValorantAPI){
    suspend fun getAgentsApi() = api.agents()
}