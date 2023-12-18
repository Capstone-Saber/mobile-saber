package com.example.saber.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saber.R
import com.example.saber.navigation.PostOfficeAppRouter
import com.example.saber.navigation.Screen
import com.example.saber.navigation.SystemBackButtonHandler

@Composable
fun TermAndCondtionsScreen(){
    Surface (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp) ){
            HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions))
        }

    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun TermAndConditionsSreenPreview(){
    TermAndConditionsSreenPreview()
}