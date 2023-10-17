package com.example.tp3state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp3state.ui.theme.Tp3StateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp3StateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WaterCount()
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun WaterCount() {
    Tp3StateTheme {
        var count by rememberSaveable { mutableStateOf(0) }

        Column {
            WaterDiplay(count = count)
            Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
                Text("Add one")
            }
        }
    }
}

@Composable
fun WaterDiplay(count: Int) {
    Tp3StateTheme {
        

        Text(text = "${count}")

    }
}