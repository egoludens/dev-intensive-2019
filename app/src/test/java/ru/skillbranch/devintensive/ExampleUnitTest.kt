package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.SECOND
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun hometask_2_makeUser_works()
    {
        val testUser = User.makeUser("Andrzei Pawlowski")
        assert(testUser is User)
        println(testUser)
    }

    @Test
    fun hometask_2_parseFullName_isCorrect()
    {
        val unaMiyaki = Utils.parseFullName("Una Miyaki")
        println(unaMiyaki)

        val michael = Utils.parseFullName("Michael")
        println(michael)

        val incognito = Utils.parseFullName("")
        println(incognito)

        val space = Utils.parseFullName(" ")
        println(space)

        val nullito = Utils.parseFullName(null)
        println(nullito)
    }

    @Test
    fun hometask_2_Date_format_isCorrect()
    {
        val testDate = Date(System.currentTimeMillis())
        println(testDate.format())
        println(testDate.format("HH:mm"))
    }

    @Test
    fun hometask_2_Date_add_isCorrect()
    {
        println(Date())
        println(Date().add(10, TimeUnits.SECOND))
        println(Date().add(-2, TimeUnits.HOUR))
    }
}
