package com.example.auth.presentation.signin

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth.R
import com.example.auth.presentation.signin.theme.InterFontFamily

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    keyboardOptions: KeyboardOptions,
    placeholder: String,
    leadingIcon: Painter,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 18.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 20.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.7f)
            )
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.White
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White.copy(alpha = 0.9f),
            focusedBorderColor = Color.White.copy(alpha = 0.4f),
            unfocusedBorderColor = colorResource(R.color.auth_field_border),
            cursorColor = Color.White
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .size(32.dp),
                painter = leadingIcon,
                contentDescription = placeholder,
                tint = Color.White
            )
        },
        trailingIcon = trailingIcon?.let { { it() } },
        prefix = { Spacer(modifier = Modifier.width(14.dp)) }
    )
}
