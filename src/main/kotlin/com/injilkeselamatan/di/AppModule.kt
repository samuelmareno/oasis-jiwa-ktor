package com.injilkeselamatan.di

import com.injilkeselamatan.audio.data.AudioRepository
import com.injilkeselamatan.audio.data.AudioRepositoryImpl
import com.injilkeselamatan.user.data.UserRepository
import com.injilkeselamatan.user.data.UserRepositoryImpl
import com.injilkeselamatan.version.data.VersionRepository
import com.injilkeselamatan.version.data.VersionRepositoryImpl
import com.mongodb.ConnectionString
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val username = System.getenv("MONGO_USERNAME")
        val password = System.getenv("MONGO_PASSWORD")
        KMongo.createClient(ConnectionString("mongodb://$username:$password@mongoserver"))
            .coroutine
            .getDatabase("oasis_jiwa_db")
    }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AudioRepository> { AudioRepositoryImpl(get()) }
    single<VersionRepository> { VersionRepositoryImpl(get()) }
}