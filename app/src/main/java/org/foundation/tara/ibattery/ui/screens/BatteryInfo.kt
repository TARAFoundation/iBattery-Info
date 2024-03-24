package org.foundation.tara.ibattery.ui.screens

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme
import org.foundation.tara.ibattery.utils.BatteryUtil

@Composable
fun BatteryInfoScreen(context: Context = LocalContext.current) {
    val batteryStatus = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { iFilter ->
        context.registerReceiver(null, iFilter)
    }

    val batteryPercent = BatteryUtil.getBatteryPercent(batteryStatus)
    val isCharging = BatteryUtil.getBatteryStatus(batteryStatus)
    Text(
        text = "isCharging: $isCharging, Percent: $batteryPercent"
    )
}


@Preview
@Composable
fun BatteryInfoScreenPreview() {
    IBatteryTheme {
        BatteryInfoScreen()
    }
}