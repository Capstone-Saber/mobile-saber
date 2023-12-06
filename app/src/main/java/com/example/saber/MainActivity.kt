package com.example.saber

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.saber.ml.Model
import com.example.saber.ml.MyModel
import com.example.saber.ui.theme.SaberTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaberTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SaberApp()
//                    predictMl(name = "namae", context = this)
//                    predictMlkkfajri(context = this)
                }
            }
        }
    }
}

@Composable
fun predictMl(name: String, context: Context, modifier: Modifier = Modifier) {

    Log.d("Tes","coba 1")
// Create a float value from the integer
    val listFloat = listOf<Float>(25F, 35F,45F,35F,20F,55F,75F,65F,35F,45F,25F,65F,25F,25F,45F,25F,25F,65F,25F,45F,25F,55F,25F,25F,35F,25F,45F,25F,35F,45F)
    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(30 * 4)
    for (floatNum in listFloat){
        byteBuffer.putFloat(floatNum)
    Log.d("Tes","coba 2")
    }
    val model = MyModel.newInstance(context)
// Create an input tensor with the float value
    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 30), DataType.FLOAT32)
    inputFeature0.loadBuffer(byteBuffer)
    Log.d("Tes","coba 3")
// Run model inference and get result
    Log.d("Tes","coba 4")
    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
    Log.d("Tes","coba 5")
// Process and interpret the predictions
    val predictions = outputFeature0.floatArray
    Log.d("Tes","coba 6")
// Release model resources
    model.close()

    Text(
        text = "Prediction: ${predictions[0]}",
        modifier = modifier
    )
}

@Composable
fun predictMlkkfajri(context: Context){
    val t1: Float = 25F
    val t2: Float = 35F
    val t3: Float = 45F
    val t4: Float = 50F
    val t5: Float = 20F


    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(5 * 4)
    byteBuffer.putFloat(t1)
    byteBuffer.putFloat(t2)
    byteBuffer.putFloat(t3)
    byteBuffer.putFloat(t4)
    byteBuffer.putFloat(t5)


    val model = Model.newInstance(context)

    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 5), DataType.FLOAT32)
    inputFeature0.loadBuffer(byteBuffer)

    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray


    Text(
        text = "Prediction: ${outputFeature0[0]}"
    )
}