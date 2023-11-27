package example2.runner

import example2.TestRunner2
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberFunctions

private const val BEFORE = "before"
private const val AFTER = "after"

class NewTestRunner<T : Any> : TestRunner2<T> {

    private lateinit var stepsMemberFunctions: Collection<KFunction<*>>
    private lateinit var steps: T


    override fun runTest(steps: T, test: () -> Unit) {
        println(">>> STARTing ${steps::class.simpleName}")

        this.steps = steps

        stepsMemberFunctions = steps::class.declaredMemberFunctions

        runAllBefore()
        test.invoke()
        runAllAfter()

        println("<<< ENDing   ${steps::class.simpleName} \n")
    }

    private fun runAllBefore() =
        stepsMemberFunctions.filter { it.name.startsWith(example.runner.BEFORE) }.forEach {
            print("call [${example.runner.BEFORE}] ${it.name} --")
            it.call(steps)
        }

    private fun runAllAfter() =
        stepsMemberFunctions.filter { it.name.startsWith(example.runner.AFTER) }.forEach {
            print("[${example.runner.AFTER}] ${it.name} --")
            it.call(steps)
        }

    fun Any.runStep(stepName: String) {
        this::class.members
            .filter { it.name.contains(stepName) }
            .forEach {
                println("Start step: ${it.name}")
                it.call(this)
                println("End step: ${it.name}")
            }
    }

    private fun T.beforeAfter(beforeAfter: String) = this::class.declaredFunctions.filter { kFunction -> kFunction.name.contains(beforeAfter) }
        .forEach { it.call(this) }

}