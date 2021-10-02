package com.thelis.facetime.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.gms.tasks.Tasks.call
import com.thelis.facetime.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.recycler_view_contact.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          /*  val btn = findViewById<ImageButton>(R.id.callBtn)

        btn.setOnClickListener {
            val intent = Intent(this, WebView::class.java)
            startActivity(intent)
        }*/

    }
}