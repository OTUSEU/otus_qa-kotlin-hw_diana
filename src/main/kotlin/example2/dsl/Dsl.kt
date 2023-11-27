package example2.dsl

import example2.runner.NewTestRunner

fun <T : Any> testRunner(initializer: NewTestRunner<T>.() -> Unit): NewTestRunner<T> = NewTestRunner<T>().also{ it.initializer() }

fun <T : Any> testGroup(func: NewTestRunner<T>.() -> Unit): NewTestRunner<T> = NewTestRunner<T>().apply(func)