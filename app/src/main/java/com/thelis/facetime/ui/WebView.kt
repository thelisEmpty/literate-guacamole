package com.thelis.facetime.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.thelis.facetime.R
import com.thelis.facetime.data.Contact
import com.thelis.facetime.data.NODE_CONTACTS
import kotlinx.android.synthetic.main.activity_web_view.*


class WebView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)



        WebViewSetup()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebViewSetup() {
        val url = intent.extras!!.getString("url")
        webview.webChromeClient = WebChromeClient()
        webview.apply {
            if (url != null) {
                loadUrl(url)
            }
            Log.d("callBtn", "Url ::  $url!!!! ")
            settings.javaScriptEnabled = true

            settings.safeBrowsingEnabled = true
        }
    }
}


