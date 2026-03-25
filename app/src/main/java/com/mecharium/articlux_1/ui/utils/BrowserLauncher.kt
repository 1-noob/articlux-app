package com.mecharium.articlux_1.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openUrlInBrowser(context: Context, url: String) {

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    context.startActivity(intent)

}