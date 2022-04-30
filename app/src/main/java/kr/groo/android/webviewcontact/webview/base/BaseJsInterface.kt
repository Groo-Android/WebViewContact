package kr.groo.android.webviewcontact.webview.base

import android.content.Context
import android.webkit.WebView

/**
 * @author groo
 * @since 2022/04/30
 */
abstract class BaseJsInterface(
    private val context: Context,
    private val webView: WebView
) {
    abstract fun getAccessName(): String
}
