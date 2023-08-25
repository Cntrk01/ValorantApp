package com.movieapp.valorantapp.agents_buddies_dataclass

data class Buddies(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val levels: List<Level>,
    val themeUuid: Any,
    val uuid: String
)