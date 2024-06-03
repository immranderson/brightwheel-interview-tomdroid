package com.tomdroid.interview.brightwheel.common_ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    com.tomdroid.interview.brightwheel.common_ui.themes.BrightwheelInterviewTheme {
        Greeting("Android")
    }
}