package rafaelacs.com.br.dogskotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rafaelacs.com.br.dogskotlin.adapter.DogAdapter


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getImageButton.setOnClickListener {
            //User launch and pass Dispatchers.Main to tell that the result
            //of this Coroutine is expected on the main thread.
            launch(Dispatchers.Main) {
                //Try catch block to handle exceptions when calling Dog API
                try {
                    val response = DogAdapter.apiClient.getRandomImage()
                    //Check if response was successful
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()!!
                        //Check for null
                        data.message?.let { imageUrl ->
                            //Load URL into the ImageView using Coil
                            dogImageView.load(imageUrl)
                        }
                    } else {
                        // Show API error
                        Toast.makeText(
                            this@MainActivity,
                            "Error occcurred: ${response.message()}", Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    //Show API error. This is the error raised by the client
                    Toast.makeText(
                        this@MainActivity, "Error occcurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

}