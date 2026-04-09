package com.example.eval_d3_p3.extensions

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: String) {
    // Simple UX feedback for navigation clicks.
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
