package example2

interface TestRunner2<T> {
    fun runTest(steps: T, test: () -> Unit)
}
