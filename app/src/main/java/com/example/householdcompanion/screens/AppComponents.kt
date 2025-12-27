package com.example.householdcompanion.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.householdcompanion.R

@Composable
fun SectionCard(
    titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val spaceM = dimensionResource(R.dimen.space_m)
    val radius = dimensionResource(R.dimen.radius_m)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(Modifier.padding(spaceM)) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(spaceM))
            content()
        }
    }
}

@Composable
fun PrimaryButton(
    textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonH = dimensionResource(R.dimen.button_h)
    Button(
        onClick = onClick,
        modifier = modifier.height(buttonH),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text = stringResource(textRes))
    }
}

@Composable
fun TopBar(
    title: String,
    left: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null
) {
    val spaceM = dimensionResource(R.dimen.space_m)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spaceM, vertical = spaceM),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.width(dimensionResource(R.dimen.topbar_side_w))) {
            left?.invoke()
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Box(Modifier.width(dimensionResource(R.dimen.topbar_side_w)), contentAlignment = Alignment.CenterEnd) {
            right?.invoke()
        }
    }
}

@Composable
fun ActionTile(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spaceM = dimensionResource(R.dimen.space_m)
    val radius = dimensionResource(R.dimen.radius_l)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(radius),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            Modifier.padding(spaceM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.chevron),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}