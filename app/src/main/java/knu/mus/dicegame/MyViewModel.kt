package knu.mus.dicegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random
class MyViewModel : ViewModel() {
    private val _liveData: MutableLiveData<List<Int>> = MutableLiveData(listOf(0,0,0,0,0))
    val liveData: LiveData<List<Int>>
        get() = _liveData

    private val _isOn: MutableLiveData<Boolean> = MutableLiveData(false)
    val isOn: LiveData<Boolean>
        get() = _isOn

    fun random(){
        _liveData.value = List(5){Random.nextInt()}
    }

    fun randomRoll(){
        while(_isOn.value == true){
            Thread.sleep(500)
            random()
        }
    }
}