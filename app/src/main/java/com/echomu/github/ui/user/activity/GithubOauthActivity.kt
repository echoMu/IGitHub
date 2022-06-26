package com.echomu.github.ui.user.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.echomu.github.R
import com.echomu.github.databinding.ActivityRegisterAndLoginBinding
import com.echomu.github.ui.BaseActivity
import com.echomu.github.ui.NoViewModel
import com.echomu.github.ui.main.activity.MainActivity


/**
 * Created by echoMu.
 */
class GithubOauthActivity : BaseActivity<ActivityRegisterAndLoginBinding, NoViewModel>() {

    override val layoutRes: Int = R.layout.activity_register_and_login
    override val viewModel = NoViewModel()
    override val containId: Int = R.id.webview
    private var webView: WebView? = null

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_oauth)

        webView = findViewById(R.id.webview)

        webView?.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
        webView?.setWebViewClient(webViewClient);

        val webSettings = webView?.getSettings()
        webSettings?.javaScriptEnabled = true //允许使用js

        webView?.loadUrl("https://github.com/login/oauth/authorize?client_id=92b0717a8283817cbf60")
    }

    private val webViewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) { //页面加载完成
            Log.d("hjb","onPageFinished")
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) { //页面开始加载
            Log.d("hjb","onPageStarted")
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            Log.d("hjb","onReceivedError")
            finish()
            startActivity(Intent(this@GithubOauthActivity, MainActivity::class.java))
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

}