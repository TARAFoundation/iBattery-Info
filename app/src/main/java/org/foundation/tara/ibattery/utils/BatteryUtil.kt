package org.foundation.tara.ibattery.utils

import android.content.Intent
import android.os.BatteryManager

object BatteryUtil {

    /**
     * @param batteryStatus - Battery Intent Filter
     * @return battery percentage as Integer
     * @author Hemanshu Varma
     */
    fun getBatteryPercent(batteryStatus: Intent?): Int {

        // Battery percent
        val batteryPercent = batteryStatus?.let { intent ->
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        } ?: 0.0f
        return batteryPercent.toInt()
    }

    /**
     * @param batteryStatus - Battery Intent Filter
     * @return status of Battery - FULL or CHARGING
     * @author Hemanshu Varma
     */
    fun getBatteryStatus(batteryStatus: Intent?): Boolean {
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
    }

    fun isUSBCharging(batteryStatus: Intent?): Boolean {
        val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        return chargePlug == BatteryManager.BATTERY_PLUGGED_USB
    }

    fun isACCharging(batteryStatus: Intent?): Boolean {
        val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        return chargePlug == BatteryManager.BATTERY_PLUGGED_AC
    }
}