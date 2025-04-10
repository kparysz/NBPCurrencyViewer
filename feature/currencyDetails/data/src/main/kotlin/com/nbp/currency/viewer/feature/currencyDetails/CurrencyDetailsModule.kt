package com.nbp.currency.viewer.feature.currencyDetails

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyDetailsModule {

    @Binds
    abstract fun providesProfileRepository(
        currencyDetailsNetworkRepository: CurrencyDetailsNetworkRepository,
    ): CurrencyDetailsRepository
}