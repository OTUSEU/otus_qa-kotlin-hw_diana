import kotlin.reflect.full.declaredMemberFunctions

fun main() {

    val new = NewTestRunner()
    val stp = Test()

//    new.runTest(stp, {println("Test")})
    new.runTest(stp) { println("Test") }

    println("Второй тест класс")
    new.runTest(Test2()) { println("Test") }



}

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)

}
class Test {

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

class Test2 {

  /*  fun test22afterEach() {
        println("Очистка данных после каждого теста22")
    }*/

    fun afterEachTest2() {
        println("Очистка данных после каждого теста2")
    }

    fun afterAllTest2() {
        println("Очистка данных после всех тестов2")
    }
    fun beforeAllTest2() {
        println("Предусловие перед всеми тестами2")
    }

    fun beforeEachTest2() {
        println("Предусловие перед каждым тестом2")
    }

}

class NewTestRunner : TestRunner {

    override fun <T> runTest(steps: T, test: () -> Unit) {

//        Test::class.declaredMemberFunctions.forEach {              // Сдано в работе - не работает с TEST2
        steps!!::class.declaredMemberFunctions.forEach {  // Должно быть
            if (it.name.startsWith("before")) {
                println(it.name)
                it.call(steps)
            }
        }

        test()

//        Test::class.declaredMemberFunctions.forEach {             // Сдано в работе - не работает с TEST2
        steps!!::class.declaredMemberFunctions.forEach {// Должно быть
            if (it.name.startsWith("after")) {
                it.call(steps)
            }
        }
    }
}

