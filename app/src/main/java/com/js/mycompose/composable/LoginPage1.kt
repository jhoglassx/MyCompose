package com.js.mycompose.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.js.mycompose.R
import com.js.mycompose.ui.theme.orangish
import com.js.mycompose.ui.theme.purplish

@Preview(showBackground = true)
@Composable
fun LoginPage1() {
    Box {
        BgCard()
        MainCard()
    }
}

@Composable
fun BgCard() {
    val signupText = buildAnnotatedString {
        append("Don't have an account? ")
        withStyle(SpanStyle(color = orangish)) {
            append("Sign up here!")
        }
    }

    Surface(
        color = purplish,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_fb),
                    contentDescription = "icon facebook"
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                    contentDescription = "icon google"
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_twitter),
                    contentDescription = "icon twitter"
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(text = signupText, color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCard() {
    val emailState = remember { mutableStateOf(TextFieldValue("test@test.com")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(
            topStart = ZeroCornerSize,
            topEnd = ZeroCornerSize
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_vaccum),
                contentDescription = "logo"
            )

            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = { Text(text = "Email address") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                modifier = modifier
            )

            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                label = { Text(text = "Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                modifier = modifier,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.4f)) {
                Text("Forgot password", textAlign = TextAlign.Center, modifier = modifier)
            }

            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(orangish),
                shape = shapes.medium,
                contentPadding = PaddingValues(8.dp),
                content = { Text(text = "Log in") }
            )
        }
    }
}
