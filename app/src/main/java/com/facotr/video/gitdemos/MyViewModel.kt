package com.facotr.video.gitdemos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var _numberss = MutableLiveData(0)
    var numerLiveData = _numberss

    fun add(n: Int){
        _numberss.value = _numbers.value?.plus(n)
    }


    fun reset(){
        _numberss.value = 0
    }

    //Datas and then so a junk foods

}
