import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.functions



fun main() {

    val new = NewTestRunner()
    val stp = Test()

    new.runTest(stp, {println("Test")})

}

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)

}
open class Test {

    open fun beforeAllTest() {
        println("Предусловие перед всеми тестами")

    }

    open fun beforeEachTest() {
        println("Предусловие перед каждым тестом")

    }

    open fun afterEachTest() {
        println("Очистка данных после каждого теста")

    }

    open fun afterAllTest() {
        println("Очистка данных после всех тестов")

    }

}

class NewTestRunner : TestRunner {

    override fun <T> runTest(steps: T, test: () -> Unit) {

        Test::class.functions.forEach {
            if (it.name.startsWith("before")) {
                it.call(steps)
            }
        }

        test()

        Test::class.functions.forEach {
            if (it.name.startsWith("after")) {
                it.call(steps)
            }
        }


    }

}

