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

    private var isOn: Boolean = false;

    init {
        thread {
            while (true) {
                Thread.sleep(500)
                if (isOn) {
                    random()
                }
            }
        }
    }

    fun signalStop()  {
        isOn = false;
    }

    fun random() {
        _liveData.postValue(List(5){
            Random.nextInt(0, 6)
        })
    }

    fun randomRoll() {
        isOn = true;
    }
}