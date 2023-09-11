package com.example.superheroapplication.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superheroapplication.R
import java.io.FileDescriptor

data class SuperHero(
    @DrawableRes val heroPic: Int,
    @StringRes val heroName: Int,
    @StringRes val heroDescriptor: Int,
    @StringRes val heroPower:Int,
)

val superheroes = listOf(
    SuperHero(R.drawable.ironman,R.string.ironmanName,R.string.ironmanDesc,R.string.ironmanPower),
    SuperHero(R.drawable.batman,R.string.batmanName,R.string.batmanDesc,R.string.batmanPower),
    SuperHero(R.drawable.superman,R.string.supermanName,R.string.supermanDesc,R.string.supermanPower),
    SuperHero(R.drawable.spiderman,R.string.spidermanName,R.string.spidermanDesc,R.string.spidermanPower),
    SuperHero(R.drawable.professorx,R.string.professorxName,R.string.professorxDesc,R.string.professorxPower),
    SuperHero(R.drawable.moonknight,R.string.moonknightName,R.string.moonknightDesc,R.string.moonknightPower),
    SuperHero(R.drawable.ghostrider,R.string.ghostriderName,R.string.ghostriderDesc,R.string.ghostriderPower),
    SuperHero(R.drawable.vemon,R.string.vemonName,R.string.vemonDesc,R.string.vemonPower),
    SuperHero(R.drawable.blackadam,R.string.blackadamName,R.string.blackadamDesc,R.string.blackadamPower),
    SuperHero(R.drawable.deadpool,R.string.deadpoolName,R.string.deadpoolDesc,R.string.deadpoolPower),
)

