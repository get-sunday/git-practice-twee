package com.facotr.video.gitdemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        number = savedInstanceState?.getInt("NUM")?: 0
        var btn = findViewById<Button>(R.id.button)


         btn.setOnClickListener {
              btn.setText("hello")
         }

         findViewById<Button>(R.id.button2).setOnClickListener {
              btn.setText("hello -1")
         }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("NUM",number)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)



    }
}
