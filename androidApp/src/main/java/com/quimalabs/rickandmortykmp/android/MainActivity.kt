package com.quimalabs.rickandmortykmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.quimalabs.rickandmortykmp.android.scenes.characters.view.CharactersView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CharactersView()
            }
        }
    }
}

