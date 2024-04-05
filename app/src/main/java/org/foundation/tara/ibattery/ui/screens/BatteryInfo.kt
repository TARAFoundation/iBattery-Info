package org.foundation.tara.ibattery.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.ui.anims.WavesIndicator
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme

@Composable
fun BatteryInfoScreen(modifier: Modifier, batteryPercent: Float, isCharging: Boolean) {
    Text(
        text = "isCharging: $isCharging, Percent: ${batteryPercent/100f}"
    )
    WavesIndicator(modifier = modifier, color = Color.Cyan, progress = batteryPercent/100f)
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BatteryInfoScreenPreview() {
    IBatteryTheme {
        BatteryInfoScreen(
            modifier = Modifier,
            batteryPercent = 56f,
            isCharging = false
        )
    }
}