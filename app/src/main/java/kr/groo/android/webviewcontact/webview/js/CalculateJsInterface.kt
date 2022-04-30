package kr.groo.android.webviewcontact.webview.js

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import kr.groo.android.webviewcontact.R
import kr.groo.android.webviewcontact.webview.base.BaseJsInterface

/**
 * @author groo
 * @since 2022/04/30
 */
class CalculateJsInterface(
    private val context: Context,
    private val webView: WebView
) : BaseJsInterface(context, webView) {

    override fun getAccessName(): String = "Android"

    /**
     * WebView -> Android
     */
    @JavascriptInterface
    fun explainTheRules() {
        Toast.makeText(context, context.getString(R.string.calculate_rules), Toast.LENGTH_SHORT).show()
    }

    /**
     * Android -> WebView
     */
    @JavascriptInterface
    fun sendCalculateResult(value: Int) {
        webView.loadUrl("javascript:sendCalculateResult($value);")
    }
}
