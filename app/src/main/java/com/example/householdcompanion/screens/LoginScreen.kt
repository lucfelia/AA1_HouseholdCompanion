package com.example.householdcompanion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.R

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

    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spaceL)
                    .padding(bottom = 160.dp),
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


        Image(
            painter = painterResource(R.drawable.dragon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 85.dp),
            contentScale = ContentScale.Fit
        )
    }
}
