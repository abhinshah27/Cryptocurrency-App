package my.demo.myapplication.presentation.di

import my.demo.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import my.demo.myapplication.BuildConfig
import my.demo.myapplication.BuildConfig.API_KEY
import my.demo.myapplication.BuildConfig.BASE_URL

/**
 * Dagger Hilt module for providing network-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Provides a Retrofit instance for making network requests
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient) // Injected OkHttpClient
            .baseUrl(BASE_URL) // Base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter factory for JSON serialization/deserialization
            .build()
    }

    // Provides a default OkHttpClient with various configurations
    @Singleton
    @Provides
    fun provideDefaultOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-CMC_PRO_API_KEY", API_KEY) // API key for authentication
                chain.proceed(request.build())
            }
            .callTimeout(Constants.CALL_TIMEOUT, TimeUnit.SECONDS) // Maximum time to wait for an HTTP call to complete
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS) // Maximum time to establish an HTTP connection
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS) // Maximum time to read data from an HTTP response
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS) // Maximum time to write data to an HTTP request
            .addInterceptor(loggingInterceptor) // Injected logging interceptor for logging HTTP request/response details
            .build()
    }

    // Provides a logging interceptor for logging HTTP request/response details
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY // Log detailed information for debugging in debug builds
            }
        }
    }
}
