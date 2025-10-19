package knu.mus.dicegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread
import kotlin.random.Random
class DiceViewModel : ViewModel() {
    private val _liveData: MutableLiveData<List<Int>> = MutableLiveData(listOf(0,0,0,0,0))
    val liveData: LiveData<List<Int>>
        get() = _liveData

    private val _isOn: MutableLiveData<Boolean> = MutableLiveData(false)

    fun signalStop(){
        _isOn.postValue(false)
    }

    fun signalStart(){
        _isOn.postValue(true)
    }


    fun random(){
        _liveData.postValue(List(5){Random.nextInt(0, 6)})
    }

    fun randomRoll(){
        thread {
            while (_isOn.value == true) {
                Thread.sleep(500)
                random()
            }
        }
    }
}