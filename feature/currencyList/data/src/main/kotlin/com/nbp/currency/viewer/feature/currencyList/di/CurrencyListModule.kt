package com.nbp.currency.viewer.feature.currencyList.di

import com.nbp.currency.viewer.feature.currencyList.AllCurrenciesRepository
import com.nbp.currency.viewer.feature.currencyList.repository.AllCurrenciesNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyListModule {

    @Binds
    abstract fun providesProfileRepository(
        allCurrenciesNetworkRepository: AllCurrenciesNetworkRepository,
    ): AllCurrenciesRepository
}
