package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
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
    fun test_data_maping() {
        val user = User.makeUser("Диван Живанов")
        val newuser = user.copy(lastVisit = Date().add(-7, TimeUnits.SECOND))
        println(newuser)

        val userView = newuser.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Диван Живанов")
        val txtMessage = BaseMessage.makeMessage(user, Chat(id = "0"), payload = "Any text message", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat(id = "0"), payload = "Any image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
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

    @Test
    fun hometask_2_Utils_toInitials_works()
    {
        println(Utils.toInitials("john" ,"doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun hometask_2_Utils_transliteration_works()
    {
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр","_")) //Amazing_Petr
    }

    @Test
    fun hometask_2_Date_humanizeDiff_works()
    {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun hometask_2_User_Builder_works()
    {
        val user:User = User.Builder().id("TEST_ID")
            .firstName("John")
            .lastName("Doe")
            .avatar(null)
            .rating(0)
            .respect(0)
            .lastVisit(Date())
            .isOnline(false)
            .build() // должен вернуть объект User
        println(user)
    }

}
