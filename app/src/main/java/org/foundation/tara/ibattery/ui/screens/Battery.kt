package org.foundation.tara.ibattery.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.foundation.tara.ibattery.ui.theme.IBatteryTheme

@Composable
fun Battery(
    modifier: Modifier = Modifier, batteryPercent: Int, outerThickness: Float = 20f, color: Color
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(0.5f)
        ) {
            val canvasWidth = this.size.width
            val canvasHeight = this.size.height

            drawRect(
                color = color, size = Size(
                    width = canvasWidth, height = canvasHeight
                ), style = Stroke(
                    width = outerThickness, pathEffect = PathEffect.cornerPathEffect(8.dp.toPx())
                )
            )

            //Battery Knob
            drawRoundRect(
                color = Color.Green, topLeft = Offset(
                    x = canvasWidth * 0.25f, y = -40f
                ), size = Size(
                    canvasWidth * 0.5f, 40f
                ), cornerRadius = CornerRadius(15f, 15f)
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BatteryPreview() {
    IBatteryTheme {
        Battery(batteryPercent = 77, color = Color.Green)
    }
}