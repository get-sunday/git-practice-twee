package com.facotr.video.gitdemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var number = 0

    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        var btn = findViewById<Button>(R.id.button)
         btn.setOnClickListener {
              btn.text == "${++(viewModel.number)}"
         }

         findViewById<Button>(R.id.button2).setOnClickListener {
             btn.text == "${--(viewModel.number)}"
         }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("NUM",number)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuAdd){
             viewModel.number  == 0
        }

        return super.onOptionsItemSelected(item)
    }


}
