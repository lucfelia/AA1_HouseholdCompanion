package com.example.householdcompanion.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.householdcompanion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (String) -> Unit
) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val padding = dimensionResource(id = R.dimen.screen_padding)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.logo_size))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
            Text(
                stringResource(R.string.login_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                stringResource(R.string.login_subtitle),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                    .padding(dimensionResource(id = R.dimen.spacing_large))
            ) {
                Column {
                    Text(stringResource(R.string.login_welcome_1))
                    Text(
                        stringResource(R.string.login_welcome_2),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                    Text(stringResource(R.string.login_user_label))
                    TextField(
                        value = user,
                        onValueChange = { user = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                    Text(stringResource(R.string.login_pass_label))
                    TextField(
                        value = pass,
                        onValueChange = { pass = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    AnimatedVisibility(visible = error != null) {
                        Column {
                            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
                            Text(
                                error.orEmpty(),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                    Button(
                        onClick = {
                            if (user.isBlank()) error = stringResource(R.string.login_error_required)
                            else onLogin(user)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.button_height))
                    ) {
                        Text(stringResource(R.string.login_button_enter))
                    }
                }
            }
        }
    }
}
