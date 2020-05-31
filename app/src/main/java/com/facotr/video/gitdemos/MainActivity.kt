package com.facotr.video.gitdemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //So a junk foods

        var btn = findViewById<Button>(R.id.button)


         btn.setOnClickListener {
              btn.setText("hello")
         }

         findViewById<Button>(R.id.button2).setOnClickListener {
              btn.setText("hello -1")
         }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)



    }
}
