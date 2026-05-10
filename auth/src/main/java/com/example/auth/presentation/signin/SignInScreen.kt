package com.example.auth.presentation.signin

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.auth.presentation.common.AuthTextField
import com.example.auth.presentation.signin.theme.InterFontFamily
import com.example.core.ui.effects.CustomButton

private const val ANIMATION_DURATION = 100

@Composable
fun SignInScreen(
    state: SignInState,
    onIntent: (SignInIntent) -> Unit
) {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f,
        animationSpec = tween(ANIMATION_DURATION)
    )

    val forgotInteractionSource = remember { MutableInteractionSource() }
    val isForgotPressed by forgotInteractionSource.collectIsPressedAsState()

    val forgotAlpha by animateFloatAsState(
        targetValue = if (isForgotPressed) 0.2f else 0.5f,
        animationSpec = tween(ANIMATION_DURATION)
    )

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
                    .align(Alignment.End)
                    .clickable(
                        interactionSource = forgotInteractionSource,
                        indication = null
                    ) { },
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = forgotAlpha)
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
                    .padding(start = 70.dp, end = 70.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onIntent(SignInIntent.ContinueWithGoogleClicked) },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp),
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = stringResource(R.string.google_icon),
                    contentScale = ContentScale.Crop,
                    alpha = alpha
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    modifier = Modifier
                        .alpha(alpha),
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
