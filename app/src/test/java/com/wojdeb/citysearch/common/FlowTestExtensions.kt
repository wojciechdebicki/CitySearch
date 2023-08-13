package com.wojdeb.citysearch.common

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope

fun <T> StateFlow<T>.executeTest(scope: TestScope, callback: (List<T>) -> Unit) {
    val values = arrayListOf<T>()

    val job = scope.launch {
        toList(values)
    }

    callback.invoke(values)

    job.cancel()
}