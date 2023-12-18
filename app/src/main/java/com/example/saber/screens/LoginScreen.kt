package com.example.saber.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saber.R
import com.example.saber.components.ButtonComponent
import com.example.saber.components.ClickableLoginTextComponent
import com.example.saber.components.DividerTextComponent
import com.example.saber.components.HeadingTextComponent
import com.example.saber.components.MyTextFieldComponent
import com.example.saber.components.NormalTextComponent
import com.example.saber.components.PasswordTextFieldComponent
import com.example.saber.components.UnderLinedTextComponent
import com.example.saber.navigation.PostOfficeAppRouter
import com.example.saber.navigation.Screen
import com.example.saber.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(){
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            NormalTextComponent(value = stringResource(id = R.string.hallo))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(40.dp))
            
            MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                painterResource (id = R.drawable.message))

            PasswordTextFieldComponent(labelValue = stringResource(id = R.string.password),
                painterResource (id = R.drawable.lock))

            Spacer(modifier = Modifier.height(40.dp))
            
            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            ButtonComponent(value = stringResource(id = R.string.login), onButtonClicked = { /*TODO*/ })
            
            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
            })
        }
    }

    SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}