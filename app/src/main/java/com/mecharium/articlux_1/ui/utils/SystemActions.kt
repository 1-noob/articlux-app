package com.mecharium.articlux_1.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import retrofit2.http.Url


fun copyToClipboard( context: Context, url: String) {

    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("Copied URL", url)

    clipboard.setPrimaryClip(clip)
}