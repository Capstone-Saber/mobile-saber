package com.example.saber.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.saber.components.TermAndCondtionsScreen
import com.example.saber.navigation.PostOfficeAppRouter
import com.example.saber.navigation.Screen
import com.example.saber.screens.LoginScreen
import com.example.saber.screens.SignUpScreen

@Composable
fun PostOfficeApp(){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Crossfade(targetState = PostOfficeAppRouter.currenntScreen) { currentState ->
            when(currentState.value){
                is Screen.SignUpScreen ->{
                    SignUpScreen()
                }
                is Screen.TermAndConditionsScreen -> {
                    TermAndCondtionsScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }
            }
            
        }
    }
}