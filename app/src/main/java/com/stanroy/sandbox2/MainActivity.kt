package com.stanroy.sandbox2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stanroy.sandbox2.speeddial.MaterialScrollExtendableSpeedDialFab
import com.stanroy.sandbox2.speeddial.SpeedDialOption
import com.stanroy.sandbox2.ui.theme.Sandbox2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sandbox2Theme {
                // A surface container using the 'background' color from the theme
                val currencyPairList = listOf<String>(
                    "EUR/PLN",
                    "USD/PLN",
                    "GBP/PLN",
                    "CHF/PLN",
                    "EUR/USD",
                    "EUR/GBP",
                    "EUR/CHF",
                    "USD/GBP",
                    "USD/CHF",
                    "GBP/CHF",
                    "EUR/JPY",
                    "USD/JPY",
                    "GBP/JPY",
                    "CHF/JPY",
                    "JPY/PLN",
                    "AUD/USD",
                    "AUD/JPY",
                    "AUD/CHF",
                    "AUD/PLN",
                    "CAD/JPY",
                    "CAD/CHF"
                )

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val scrollState = rememberScrollState()
                    val textComp = @Composable { Text(text = "wymiana waluty") }
                    var speedDialOpen by remember { mutableStateOf(false) }
                    Log.d("TAG_TAG", " speedDialOpen $speedDialOpen")
                    val speedDialOptions = listOf<SpeedDialOption>(
                        SpeedDialOption(icon = R.drawable.email, text = "email") {},
                        SpeedDialOption(icon = R.drawable.plane, text = "plane") {},
                        SpeedDialOption(icon = R.drawable.arrow_back, text = "arrow") {},
                    )
                    Box {
                        Column(modifier = Modifier.verticalScroll(scrollState, !speedDialOpen)) {
                            currencyPairList.forEach { currencyPair ->
                                Greeting(name = currencyPair)
                                Divider()
                            }
                        }
                        MaterialScrollExtendableSpeedDialFab(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp),
                            scrollState = scrollState,
                            speedDialOptions = speedDialOptions.toTypedArray(),
                            icon = { Icon(painter = painterResource(id = R.drawable.email), contentDescription = null) },
                            text = textComp,
                            onSpeedDialOpen = {
                                speedDialOpen = it
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(32.dp),
        text = name,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sandbox2Theme {
        Greeting("Android")
    }
}