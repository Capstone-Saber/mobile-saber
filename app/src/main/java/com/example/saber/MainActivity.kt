package com.example.saber

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.saber.ml.Model
import com.example.saber.ml.ModelKwh2
import com.example.saber.ml.SavedModel

import com.example.saber.ui.theme.SaberTheme
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.support.tensorbuffer.TensorBufferFloat
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaberTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SaberApp()
                    Ngemodel(this)
                }
            }
        }
    }
}


@Composable
fun Ngemodel(context: Context) {
    val model = Model.newInstance(context)

    Log.d("try", "model diinisialisasi")
    val floatArray: FloatArray = floatArrayOf(
        0.61829F,
        0.15794335F,
        0.04684074F,
        0.9778534F,
        0.9954938F,
        0.829866F,
        0.29919064F,
        0.08120253F,
        0.69907385F,
        0.4419644F,
        0.1068693F,
        0.499926F,
        0.01454088F,
        0.9361763F,
        0.25091028F,
        0.6762042F,
        0.30666682F,
        0.52614576F,
        0.5542102F,
        0.17303863F,
        0.9996574F,
        0.79482585F,
        0.9679658F,
        0.92090964F,
        0.60813236F,
        0.94940025F,
        0.07153291F,
        0.18476108F,
        0.02595822F,
        0.32101306F
    )

    val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(30 * 4)
    byteBuffer.putFloat(0, 0.61829F)
    byteBuffer.putFloat(1, 0.15794335F)
    byteBuffer.putFloat(2, 0.04684074F)
    byteBuffer.putFloat(3, 0.9778534F)
    byteBuffer.putFloat(4, 0.9954938F)
    byteBuffer.putFloat(5, 0.829866F)
    byteBuffer.putFloat(6, 0.29919064F)
    byteBuffer.putFloat(7, 0.08120253F)
    byteBuffer.putFloat(8, 0.69907385F)
    byteBuffer.putFloat(9, 0.4419644F)
    byteBuffer.putFloat(10, 0.1068693F)
    byteBuffer.putFloat(11, 0.499926F)
    byteBuffer.putFloat(12, 0.01454088F)
    byteBuffer.putFloat(13, 0.9361763F)
    byteBuffer.putFloat(14, 0.25091028F)
    byteBuffer.putFloat(15, 0.6762042F)
    byteBuffer.putFloat(16, 0.30666682F)
    byteBuffer.putFloat(17, 0.52614576F)
    byteBuffer.putFloat(18, 0.5542102F)
    byteBuffer.putFloat(19, 0.17303863F)
    byteBuffer.putFloat(20, 0.9996574F)
    byteBuffer.putFloat(21, 0.79482585F)
    byteBuffer.putFloat(22, 0.9679658F)
    byteBuffer.putFloat(23, 0.92090964F)
    byteBuffer.putFloat(24, 0.60813236F)
    byteBuffer.putFloat(25, 0.94940025F)
    byteBuffer.putFloat(26, 0.07153291F)
    byteBuffer.putFloat(27, 0.18476108F)
    byteBuffer.putFloat(28, 0.02595822F)
    byteBuffer.putFloat(29, 0.32101306F)


// Creates inputs for reference.
    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 30), DataType.FLOAT32)
    inputFeature0.loadArray(floatArray)

// Runs model inference and gets result.
    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

    Column{
        outputFeature0.forEachIndexed { index, value ->
            Text(text = "Output[$index]: $value")
        }
    }


// Releases model resources if no longer used.
    model.close()

}

