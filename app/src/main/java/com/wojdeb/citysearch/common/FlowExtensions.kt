package com.wojdeb.citysearch.common

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.observe(scope: LifecycleCoroutineScope, callback: (T) -> Unit) {
    scope.launchWhenStarted {
        collect {
            callback.invoke(it)
        }
    }
}