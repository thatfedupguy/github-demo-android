package com.example.github_demo_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.github_demo_android.di.IoDispatcher
import com.example.github_demo_android.di.MainDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var progressDialog: CustomProgressDialog

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this,R.color.black);
    }

//    @Composable
//    fun MyComposableExample() {
//        Column(
//            verticalArrangement = Arrangement.spacedBy(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            var buttonColor by remember {
//                mutableStateOf("unknown")
//            }
//            Button(
//                onClick = {
//                    buttonColor = "Red"
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//                Text(text = "Red Button")
//            }
//            Button(
//                onClick = {
//                    buttonColor = "Black"
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
//            ) {
//                Text(text = "Black Button")
//            }
//            Timer(buttonColor)
//        }
//    }
//
//    @Composable
//    private fun Timer(buttonColor: String) {
//        val timeDuration = 5000L
//        val updatedButtonColor by rememberUpdatedState(newValue = buttonColor)
//        println(buttonColor)
//        LaunchedEffect(key1 = Unit, block = {
//            startTimer(timeDuration) {
//                println("Time ended")
//                println("Last button pressed was $buttonColor")
//                println("Last button pressed was $updatedButtonColor")
//            }
//        })
//    }
//
//    private suspend fun startTimer(timeDuration: Long, onTimeEnd: () -> Unit) {
//        delay(timeDuration)
//        onTimeEnd()
//    }
//
//    @Composable
//    fun Example() {
//        //var state by remember { mutableStateOf("Loading ...") }
//        val coroutineScope = rememberCoroutineScope()
//        val state by produceState(initialValue = "Loading...", producer = {
//            delay(9000)
//            value = "Success"
//        })
//        //val state by rememberUpdatedState(newValue = "")
//        val transition = rememberInfiniteTransition(label = "")
//        val rotation by transition.animateFloat(
//            initialValue = 0f,
//            targetValue = 90f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(2000),
//                repeatMode = RepeatMode.Restart
//            ), label = ""
//        )
////        LaunchedEffect(key1 = Unit, block = {
////            delay(9000)
////            state = "Success"
////
////        })
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(40.dp)
//        ) {
//            Text(
////                modifier = Modifier.clickable {
////                    coroutineScope.launch(ioDispatcher) {
////                        delay(9000)
////                        state = "Success"
////                    }
////                },
//                text = state,
//                style = interMedium.titleMedium.copy(fontSize = 16.sp, color = Color.Black)
//            )
//            Box(
//                modifier = Modifier
//                    .graphicsLayer(
//                        rotationZ = rotation
//                    )
//                    .size(80.dp, 80.dp)
//                    .background(color = Color.Cyan),
//            ) {
//
//            }
//        }
//    }

    fun showLoader() {
        progressDialog.run {
            show()
            setCancelable(false)
        }
    }

    fun hideLoader() {
        progressDialog.hide()
    }
}

