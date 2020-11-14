package rafaelacs.com.br.dogskotlin.adapter

import okhttp3.OkHttpClient
import rafaelacs.com.br.dogskotlin.model.DogApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogAdapter {

    val apiClient: DogApiClient = Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApiClient::class.java)

}