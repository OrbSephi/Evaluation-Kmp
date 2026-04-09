package com.example.eval_d3_p3.di

import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun initKoin() {
    // Guard against double initialization on multiple entrypoints.
    if (GlobalContext.getOrNull() != null) {
        return
    }

    startKoin {
        modules(appModule)
    }
}
