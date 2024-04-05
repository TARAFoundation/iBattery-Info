package org.foundation.tara.ibattery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.receivers.SystemBroadcastReceiver
import org.foundation.tara.ibattery.ui.screens.BatteryInfoScreen
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme
import org.foundation.tara.ibattery.utils.BatteryUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IBatteryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BatteryInfoApp()
                }
            }
        }
    }
}

// Base App Composable
@Composable
fun BatteryInfoApp(modifier: Modifier = Modifier) {
    val TAG = "MainActivity"

    // TODO: Actions to consider - BATTERY_LOW, BATTERY_OKAY, ACTION_POWER_CONNECTED, ACTION_POWER_DISCONNECTED
    var batteryPercent by remember {
        mutableFloatStateOf(0f)
    }
    var isCharging by remember {
        mutableStateOf(false)
    }

    SystemBroadcastReceiver(systemAction = Intent.ACTION_BATTERY_CHANGED) { receiveIntent ->
        val action = receiveIntent?.action ?: return@SystemBroadcastReceiver
        if (action == Intent.ACTION_BATTERY_CHANGED) {
            // Get updated battery info/stats
            Log.d(TAG, "Battery stats changed")
            batteryPercent = BatteryUtil.getBatteryPercent(receiveIntent)
            isCharging = BatteryUtil.getBatteryStatus(receiveIntent)
            Log.d(TAG, "isCharging: $isCharging, Percent: $batteryPercent")
        }
    }
    BatteryInfoScreen(modifier = modifier, batteryPercent, isCharging)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IBatteryTheme {
        BatteryInfoApp()
    }
}