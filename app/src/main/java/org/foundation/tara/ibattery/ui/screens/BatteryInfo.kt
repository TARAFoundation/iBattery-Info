package org.foundation.tara.ibattery.ui.screens

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme

@Composable
fun BatteryInfoScreen(context: Context = LocalContext.current) {
    val batteryStatus = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { iFilter ->
        context.registerReceiver(null, iFilter)
    }
    val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
    val isCharging: Boolean =
        status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

    // is power adaptor connected?
    val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
    val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
    val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

    // Battery percent
    val batteryPercent = batteryStatus?.let { intent ->
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        level * 100 / scale.toFloat()
    }

    Text(
        text = "isCharging: $isCharging, usbCharge: $usbCharge, acCharge: $acCharge, Percent: $batteryPercent"
    )
}

@Preview
@Composable
fun BatteryInfoScreenPreview() {
    IBatteryTheme {
        BatteryInfoScreen()
    }
}