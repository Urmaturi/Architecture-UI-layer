package com.example.android.guesstheword.screens

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
init
{
    Log.i("GameViewModel", "GameViewModelCreated!")
}

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel destroyed")
    }
}