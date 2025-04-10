package com.nbp.currency.viewer.core.nbpapi.data.di

import com.nbp.currency.viewer.core.nbpapi.data.NbpService
import com.nbp.currency.viewer.core.nbpapi.data.NetworkNbpRepository
import com.nbp.currency.viewer.core.nbpapi.domain.NbpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNbpRepository(
        service: NbpService,
    ): NbpRepository = NetworkNbpRepository(service)
}
