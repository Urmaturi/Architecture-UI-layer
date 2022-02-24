package com.example.android.guesstheword.screens

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 6000L
    }
    // The current word
//    private  val _timer = MutableLiveData<CountDownTimer>()
//    val timer : LiveData<CountDownTimer>
//    get() = _timer

    private val timer: CountDownTimer

    private val _Time = MutableLiveData<Long>()
    val Time: LiveData<Long>
        get() = _Time


    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word


    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    init {
        _eventGameFinish.value = false
        Log.i("GameViewModel", "GameViewModelCreated!")
        resetList()
        nextWord()
        _score.value = 0
        _word.value = ""

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _Time.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _Time.value = DONE
                _eventGameFinish.value = true
            }

        }
        //  DateUtils.formatElapsedTime()

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()

        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }


    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
            // _eventGameFinish.value = true
        }
        _word.value = wordList.removeAt(0)


    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishCompleate() {
        _eventGameFinish.value = false
    }


}