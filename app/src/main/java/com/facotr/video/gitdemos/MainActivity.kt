package com.facotr.video.gitdemos

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var numbesfsdfr = 0
    lateinit var text: TextView;

    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var btns = findViewById<Button>(R.id.button)
//        var btn = findViewById<Button>(R.id.text)
        text = findViewById<TextView>(R.id.txt_value)
        test()

    }


    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt("NUM",number)
        super.onSaveInstanceState(outState)
    }

    fun test() {
        //创建一个SpannableString对象
        var sStr = SpannableString("最是那一低头的温柔，像一朵水莲花不胜凉风的娇羞，道一声珍重，道一声珍重，那一声珍重里有蜜甜的忧愁");
        //设置字体(default,default-bold,monospace,serif,sans-serif)
        sStr.setSpan(StyleSpan(Typeface.BOLD), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（绝对值,单位：像素）,第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素
        sStr.setSpan(AbsoluteSizeSpan(20), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(sStr.toString())


    }


    fun testSundayName() {
        //So a junk food
        var buffer = StringBuffer();
        buffer.append("1")
            .append("2")
            .append("3")

    }




}
