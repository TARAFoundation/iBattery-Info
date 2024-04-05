package org.foundation.tara.ibattery.viewmodels

import android.content.Intent
import android.os.BatteryManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BatteryInfoViewModel : ViewModel() {

    private val _batteryPercent: MutableStateFlow<Float> = MutableStateFlow(0f)
    val batteryPercent: StateFlow<Float> get() = _batteryPercent
    var isCharging by mutableStateOf(false)
        private set

    /**
     * @param batteryStatus - Battery Intent Filter
     *
     * Calculates - battery percentage in Float
     * @author Hemanshu Varma
     */
    fun updateBatteryPercent(batteryStatus: Intent?) {

        // Battery percent
        _batteryPercent.value = batteryStatus?.let { intent ->
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        } ?: 0.0f
    }

    /**
     * @param batteryStatus - Battery Intent Filter
     *
     * Calculates - status of Battery from intent - FULL or CHARGING
     * @author Hemanshu Varma
     */
    fun updateBatteryChargeStatus(batteryStatus: Intent?) {
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        isCharging =
            (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL)
    }

}