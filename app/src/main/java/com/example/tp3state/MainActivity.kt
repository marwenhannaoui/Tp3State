package com.example.tp3state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
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

                    WellnessScreen()
                }
            }
        }
    }
}





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



@Composable
fun WellnessTaskItem(
    task:WellnessTask,
    onCheckChange:(WellnessTask)->Unit,
    modifier: Modifier = Modifier
) {
//    var checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = task.label
        )
        Checkbox(
                checked = task.checkedState.value,
        onCheckedChange = {onCheckChange(task)}
        )
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    oncheckChange:(WellnessTask)->Unit,
    modifier: Modifier = Modifier
) { LazyColumn(
    modifier = modifier
) {
    items(
        items = list,
        key = { task -> task.id })
    { task ->
        WellnessTaskItem( task = task, onCheckChange = { oncheckChange(task) } )
    }
}
}

class WellnessViewModel: ViewModel()
{
    private val _statelist= getWellnessTasks().toMutableStateList();
    val statelist:List<WellnessTask>
        get() = _statelist
    fun onCheckChange(item: WellnessTask)
    {
        _statelist.toList().find { it.id==item.id }?.let{
            it.checkedState.value=!it.checkedState.value
        }
    }
}
private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
@Preview(showBackground = true)
@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
) {
    val wellnessViewModel=WellnessViewModel()
    Column(modifier = modifier) { WellnessTasksList(
        list = wellnessViewModel.statelist,
        oncheckChange =  {wellnastask->wellnessViewModel.onCheckChange(wellnastask)}       )
    }
}





