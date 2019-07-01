package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val nameFragments : List<String>? = fullName?.split(" ")

        var firstName: String? = nameFragments?.getOrNull(0)
        var lastName: String? = nameFragments?.getOrNull(1)

        firstName = if(firstName == "") null else firstName
        lastName = if(lastName == "") null else lastName

        return Pair(firstName, lastName)
    }


}