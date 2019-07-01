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

    fun transliteration(payload: String, divider: String = " "): String
    {
        val translitData = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
           )
        val parts: List<String> = payload.split(" ")
        var newParts: MutableList<String> = arrayListOf()
        for (part:String in parts)
        {
            var newPart: String = "";
            part.forEach {
                val nextSymbol:String = translitData[it.toString().toLowerCase()] ?: it.toString()
                newPart += if (it.isUpperCase()) nextSymbol.substring(0, 1).toUpperCase() else nextSymbol.substring(0, 1).toLowerCase()
                newPart += if (nextSymbol.length > 1) nextSymbol.substring(1) else ""
            }
            newParts.add(newPart)
        }
        return newParts.joinToString(divider)
    }

}