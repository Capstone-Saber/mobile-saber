package com.example.saber.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(){
    object SignUpScreen : Screen()
    object TermAndConditionsScreen : Screen()
    object LoginScreen : Screen()

}
object PostOfficeAppRouter {
    val currenntScreen : MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination : Screen){
        currenntScreen.value = destination
    }
}