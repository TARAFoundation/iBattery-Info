package org.foundation.tara.ibattery.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.foundation.tara.ibattery.ui.anims.WavesIndicator
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme
import org.foundation.tara.ibattery.ui.theme.Red80
import org.foundation.tara.ibattery.ui.theme.Yellow90

@Composable
fun BatteryInfoScreen(modifier: Modifier, batteryPercent: Float, isCharging: Boolean) {

    val batteryColor =
        animateColorAsState(
            targetValue = if (batteryPercent > 30) Color.Green else if (batteryPercent <= 30 && batteryPercent > 15) Yellow90 else Red80,
            label = "Battery Percent Color",
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = 700,
                easing = LinearEasing
            )
        )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f),
            border = BorderStroke(
                3.dp, Color.Gray
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                WavesIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = batteryColor.value,
                    progress = batteryPercent / 100f
                )
                Text(
                    text = batteryPercent.toInt().toString(),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium
                )
            }

        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = "isCharging: $isCharging, Percent: $batteryPercent"
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BatteryInfoScreenPreview() {
    IBatteryTheme {
        BatteryInfoScreen(
            modifier = Modifier,
            batteryPercent = 80f,
            isCharging = false
        )
    }
}