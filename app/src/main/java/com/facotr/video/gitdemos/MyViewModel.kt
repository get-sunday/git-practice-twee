package com.facotr.video.gitdemos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    var number = 0
    private var _number = MutableLiveData(0)
    var numerLiveData = _number

    fun add(n: Int){
        _number.value = _number.value?.plus(n)
    }


    fun reset(){
        _number.value = 0
    }

    //Datas and then so a junk foods

}