package org.foundation.tara.ibattery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.foundation.tara.ibattery.ui.screens.BatteryInfoScreen
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme

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
    val context = LocalContext.current
    BatteryInfoScreen(context = context)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IBatteryTheme {
        BatteryInfoApp()
    }
}