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

    fun toInitials(firstName: String?, lastName: String?): String?
    {
        var initials: String? = ""
        for (part:String? in arrayOf(firstName, lastName))
        {
            initials += when
                {
                    part == null -> ""
                    part.trim() == "" -> ""
                    else -> part.substring(0, 1).toUpperCase()
                }
        }
        initials = if(initials == "") null else initials
        return initials
    }

}