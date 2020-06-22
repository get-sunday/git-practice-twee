package com.facotr.video.gitdemos

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var _numberss= MutableLiveData(0)
    private var _numbers1123s = MutableLiveData(0)
    private var _numberssssdf = MutableLiveData(0)
    private var _number02834ssdfss = MutableLiveData(0)
    var numerLiveData = _numberss

    fun adds(n: Int){
        _numberss.value = _numberss.value?.plus(n)
    }
    fun add(n: Int){
        _numberss.value = _numberss.value?.plus(n)
    }


    fun reset(){
        _numberss.value = 0
    }

    //Methods
    fun fixMethod(){

    }

    /**
     * fix 分支提交代码，然后测试运行通过之后，再提交
     */
    fun testFunsName(){

    }

    fun getNamessss(){ //Sunday a developers and then so a junk foods

    }

    //
    fun fixFunTweeName(){

    }

    fun fixFunThreeName(){
         //So a junk foods you can see a juk ode aysd
    }

    fun fixFunFourNames(){
         //So a junk foods you can see a juk ode aysd
        TODO()
    }


    /**
     *
     */
    class Adapters : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            TODO("Not yet implemented")
        }

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

    }
    /**
     */
    class Adapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            TODO("Not yet implemented")
        }

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

    }
}
