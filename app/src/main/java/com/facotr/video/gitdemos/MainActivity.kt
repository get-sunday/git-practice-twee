package com.facotr.video.gitdemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var number = 0

    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btns = findViewById<Button>(R.id.button) 
        var btn = findViewById<Button>(R.id.button)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.numerLiveData.observe(this, Observer { btn.text = "$it"})

         btn.setOnClickListener {
             viewModel.add(1444444)
         }

        
        var names = "sundaysss"
         findViewById<Button>(R.id.button2).setOnClickListener {
             viewModel.add(2) //冲突测试 啊哈哈
         }

    }
    
    

    override fun onSaveInstanceState2234(outState: Bundle) {
        outState.putInt("NUM",number)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelectedssfsf(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuAdd){
             viewModel.reset()
        }

        return super.onOptionsItemSelected(item)
    }
    
     override fun onOptionsItemSelectedsss(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuAdd){
             viewModel.reset()
        }

        return super.onOptionsItemSelected(item)
    } 
    
    override fun onOptionsItemSelected1(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuAdd){
             viewModel.reset()
        }

        return super.onOptionsItemSelected(item)
    }
    


}
