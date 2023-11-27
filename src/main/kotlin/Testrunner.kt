import kotlin.reflect.full.declaredMemberFunctions

fun main() {

    val new = NewTestRunner()
    val stp = AllTest()
    println("### start main ### \n ")

    new.runTest(stp) { println("Test") }

    println("Второй тест класс")
    new.runTest(OnlyAfterTest()) { println("Test") }

    println("Третий тест класс")
    new.runTest(OnlyBeforeTest()) { println("Test") }

    println("### finish main ###")


}

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)

}
class AllTest {

    fun beforeAllTest() {
        println("Предусловие перед всеми тестами")
    }

    fun beforeEachTest() {
        println("Предусловие перед каждым тестом")
    }

    fun afterEachTest() {
        println("Очистка данных после каждого теста")
    }

    fun afterAllTest() {
        println("Очистка данных после всех тестов")
    }
}

class OnlyAfterTest {


    fun afterEachTest2() {
        println("Очистка данных после каждого теста2")
    }

    fun afterAllTest2() {
        println("Очистка данных после всех тестов2")
    }

}

class OnlyBeforeTest {


    fun beforeEachTest2() {
        println("Очистка данных после каждого теста2")
    }

    fun beforeAllTest2() {
        println("Очистка данных после всех тестов2")
    }

}

class NewTestRunner : TestRunner {



    override fun <T> runTest(steps: T, test: () -> Unit) {


        steps!!::class.declaredMemberFunctions.forEach {
            if (it.name.startsWith("before")) {
                println(it.name)
                it.call(steps)
            }
        }

        test()


        steps!!::class.declaredMemberFunctions.forEach {
            if (it.name.startsWith("after")) {
                it.call(steps)
            }
        }
    }
}

