package com.movieapp.valorantapp.agents_buddies_dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Agents(
    val abilities: @RawValue List<Ability>?,
    val assetPath: String?,
    val background: String?,
    val backgroundGradientColors: @RawValue List<String>?,
    val bustPortrait: String?,
    val characterTags: @RawValue Any?,
    val description: String?,
    val developerName: String?,
    val displayIcon: String?,
    val displayIconSmall: String?,
    val displayName: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val isAvailableForTest: Boolean?,
    val isBaseContent: Boolean?,
    val isFullPortraitRightFacing: Boolean?,
    val isPlayableCharacter: Boolean?,
    val killfeedPortrait: String?,
    val role: @RawValue Role?,
    val uuid: String?,
    val voiceLine: @RawValue VoiceLine?
) : Parcelable
