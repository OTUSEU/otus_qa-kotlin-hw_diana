package example2.tests

class BeforeAndAfterClass {
    fun beforeFirst() {
        "before first".log()
    }


    fun afterFirst() {
        "after first".log()
    }

    fun beforeSecond() {

        "before second".log()
    }

    fun testTwiceTestClass1 () {

        "testTwiceTestClass_1".log()
    }

    fun afterSecond() {
        "after second".log()
    }
}

class OnlyBeforeAndAfterClass {
    fun beforeAlone() {
        "before".log()
    }

    fun afterAlone() {
        "after".log()
    }
}

class AfterClass {
    fun afterOnly() {
        "after".log()
    }
}

class BeforeClass {
    fun beforeOnly() {
        "before".log()
    }
}

class EmptyTestClass

fun String.log() {
    println("-> $this running...")
}