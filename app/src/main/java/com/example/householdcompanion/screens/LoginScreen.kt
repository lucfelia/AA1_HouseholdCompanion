package com.example.householdcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.R
import com.example.householdcompanion.screens.PrimaryButton
import com.example.householdcompanion.screens.SectionCard
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (String) -> Unit
) {
    val spaceL = dimensionResource(R.dimen.space_l)
    val spaceM = dimensionResource(R.dimen.space_m)
    val spaceS = dimensionResource(R.dimen.space_s)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spaceL),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(spaceS))
            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(spaceL))

            SectionCard(titleRes = R.string.login_welcome) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it; error = null },
                    label = { Text(stringResource(R.string.login_user)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(spaceS))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it; error = null },
                    label = { Text(stringResource(R.string.login_pass)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (error != null) {
                    Spacer(Modifier.height(spaceS))
                    Text(
                        text = error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(spaceM))
                PrimaryButton(
                    textRes = R.string.login_enter,
                    onClick = {
                        if (username.isBlank()) {
                            error = context.getString(R.string.error_required_user)
                        } else {
                            onLogin(username.trim())
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}