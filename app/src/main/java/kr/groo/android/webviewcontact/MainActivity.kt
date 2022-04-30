package kr.groo.android.webviewcontact

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import kr.groo.android.webviewcontact.databinding.ActivityMainBinding
import kr.groo.android.webviewcontact.webview.js.CalculateJsInterface

class MainActivity : AppCompatActivity() {

    companion object {
        private const val URL = "file:///android_asset/calculate.html"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var calculateJsInterface: CalculateJsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        calculateJsInterface = CalculateJsInterface(this@MainActivity, binding.webview)

        initWebView()
    }

    fun onClickBtn(view: View) {
        if (binding.etNum.text.isBlank() || binding.etNum.text.toString() == "0") return

        val value = binding.etNum.text.toString().toInt()
        when (view.id) {
            R.id.btn_add -> calculateJsInterface.sendCalculateResult(value + 100)
            R.id.btn_sub -> calculateJsInterface.sendCalculateResult(value - 25)
            R.id.btn_multi -> calculateJsInterface.sendCalculateResult(value * 6)
            R.id.btn_div -> calculateJsInterface.sendCalculateResult(value / 40)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webview.apply {
            loadUrl(URL)
            settings.javaScriptEnabled = true // XSS 취약성 발생 가능
            addJavascriptInterface(calculateJsInterface, calculateJsInterface.getAccessName())
        }
    }
}
