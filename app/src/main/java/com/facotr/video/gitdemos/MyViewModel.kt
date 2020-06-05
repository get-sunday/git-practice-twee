package com.facotr.video.gitdemos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var _numbers = MutableLiveData(0)
    var numerLiveData = _numbers

    fun add(n: Int){
        _numbers.value = _numbers.value?.plus(n)
    }


    fun reset(){
        _numbers.value = 0
    }

    //Datas and then so a junk foods

}