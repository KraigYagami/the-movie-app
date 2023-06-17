package com.example.themovieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.themovieapp.ui.theme.TheMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                scope.launch {
                    val response = retrofit.create(ApiTest::class.java).popularMovies()

                    if (response.isSuccessful) {
                        val body = response.body()
                        println(body?.string())
                    } else {
                        println(response.errorBody()?.string())
                    }
                }
            }

            TheMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

interface ApiTest {
    @GET("movie/popular")
    suspend fun popularMovies(): Response<ResponseBody>
}
