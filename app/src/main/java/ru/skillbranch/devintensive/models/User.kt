package ru.skillbranch.devintensive.models

import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false)
{
    constructor(id: String, firstName: String?, lastName: String?) : this (
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    companion object Factory {
        fun makeUser(fullName: String) : User
        {
            val nameFragments : List<String> = fullName.split(" ")
            return User(
                UUID.randomUUID().toString(),
                nameFragments.getOrNull(0) ?: "",
                nameFragments.getOrNull(1) ?: ""
            )
        }
    }

}