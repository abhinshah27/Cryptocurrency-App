package my.demo.myapplication.presentation.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import my.demo.myapplication.data.datasource.remote.ApiServices
import my.demo.myapplication.data.datasource.remote.RemoteDataSourceImpl
import my.demo.myapplication.data.repositoryImpl.CryptoRepositoryImpl
import my.demo.myapplication.data.repository.CryptoRepository
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
/**
 * Dagger Hilt module for providing dependencies in the application
 */
object AppModule {

    // Provides a singleton instance of Gson for JSON serialization/deserialization
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    // Provides a singleton instance of ApiServices using RetrofitClient
    @Singleton
    @Provides
    fun provideApiServices(retrofitClient: Retrofit): ApiServices {
        return retrofitClient.create(ApiServices::class.java)
    }

    // Provides a singleton instance of CryptoRepository
    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApiServices, // ApiServices dependency
        @ApplicationContext appContext: Context, // Application Context dependency
    ): CryptoRepository {
        //instance of RemoteDataSourceImpl with the provided ApiServices
        val remoteDataSourceImpl = RemoteDataSourceImpl(api)

        // Create and return an instance of CryptoRepositoryImpl with dependencies
        return CryptoRepositoryImpl(
            remoteDataSourceImpl, // RemoteDataSourceImpl dependency
            appContext, // Application Context dependency
        )
    }
}
