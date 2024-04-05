package org.foundation.tara.ibattery

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.receivers.SystemBroadcastReceiver
import org.foundation.tara.ibattery.ui.screens.BatteryInfoScreen
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme
import org.foundation.tara.ibattery.viewmodels.BatteryInfoViewModel

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

    // TODO: Actions to consider - BATTERY_LOW, BATTERY_OKAY, ACTION_POWER_CONNECTED, ACTION_POWER_DISCONNECTED
    val viewModel: BatteryInfoViewModel = viewModel()
    val percentBattery by viewModel.batteryPercent.collectAsState()
    val chargeStatus = viewModel.isCharging

    SystemBroadcastReceiver(systemAction = Intent.ACTION_BATTERY_CHANGED) { receiveIntent ->
        val action = receiveIntent?.action ?: return@SystemBroadcastReceiver
        if (action == Intent.ACTION_BATTERY_CHANGED) {
            // Get updated battery info/stats
            viewModel.updateBatteryPercent(receiveIntent)
            viewModel.updateBatteryChargeStatus(receiveIntent)
        }
    }
    BatteryInfoScreen(modifier = modifier, percentBattery, chargeStatus)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IBatteryTheme {
        BatteryInfoApp()
    }
}