package com.facotr.video.gitdemos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var _numberss= MutableLiveData(0)
    private var _numberssss = MutableLiveData(0)
    private var _numbersss1 = MutableLiveData(0)
    private var _numbersss = MutableLiveData(0)
    var numerLiveData = _numberss

    fun add(n: Int){
        _numberss.value = _numberss.value?.plus(n)
    }


    fun reset(){
        _numberss.value = 0
    }

    //Datas and then so a junk foods

}
