package rafaelacs.com.br.dogskotlin.model

import retrofit2.Response
import retrofit2.http.GET

interface DogApiClient {

    @GET("/api/breeds/image/random")
    suspend fun getRandomImage() : Response<DogImageModel>

}