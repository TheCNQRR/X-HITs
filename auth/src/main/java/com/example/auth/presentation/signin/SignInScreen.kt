package com.example.auth.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth.R
import com.example.auth.presentation.signin.theme.InterFontFamily
import com.example.core.ui.effects.CustomButton

@Composable
fun SignInScreen() {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.ic_auth_background),
            contentDescription = stringResource(R.string.background),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(top = 200.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = stringResource(R.string.login),
                fontSize = 60.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(top = 18.dp, start = 16.dp),
                text = stringResource(R.string.login_to_continue),
                fontSize = 20.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.7f)
            )

            AuthTextField(
                state = emailState,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                placeholder = stringResource(R.string.email),
                leadingIcon = painterResource(R.drawable.ic_email)
            )

            AuthTextField(
                state = passwordState,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                placeholder = stringResource(R.string.password),
                leadingIcon = painterResource(R.drawable.ic_password)
            )

            Text(
                modifier = Modifier
                    .padding(top = 10.dp, end = 32.dp)
                    .align(Alignment.End),
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.5f)
            )
        }


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 98.dp)
        ) {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = colorResource(R.color.sign_in_button_background),
                backgroundAlpha = 0.2f,
                pressedBackgroundAlpha = 0.1f,
                iconTint = Color.White,
                contentAlignment = Alignment.Center,
                onClick = { },
                text = stringResource(R.string.login),
                textColor = Color.White,
                textStyle = TextStyle(
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 42.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(start = 70.dp, end = 70.dp)
                    .clickable {  },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp),
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = stringResource(R.string.google_icon),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = stringResource(R.string.continue_with_google),
                    fontSize = 20.sp,
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
