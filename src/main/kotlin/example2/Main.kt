package example2

import example2.dsl.testRunner
import example2.tests.*

fun main() {
    println("### start main ### \n ")


    testRunner {
        runTest(BeforeAndAfterClass()) { println("§ invoke test BeforeAndAfterClass RUNNING") }
    }

    testRunner {
        runTest(OnlyBeforeAndAfterClass()) { println("§ invoke test OnlyBeforeAndAfterClass RUNNING") }
    }

    testRunner{
        runTest(AfterClass()) { println("§ invoke test AfterClass RUNNING") }
    }

    testRunner {
        runTest(BeforeClass()) { println("§ invoke test BeforeClass RUNNING") }
    }

    testRunner {
        runTest(EmptyTestClass()) { println("§ invoke test EmptyTestClass RUNNING") }
    }

    println("### finish main ###")
}

