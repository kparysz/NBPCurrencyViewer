package com.nbp.currency.viewer.feature.currencyList

import com.nbp.currency.viewer.core.nbpapi.domain.GetAllNbpCurrenciesUseCase
import com.nbp.currency.viewer.core.nbpapi.domain.Result
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyRatesResponse
import com.nbp.currency.viewer.core.nbpapi.domain.model.NbpCurrencyTableAResponse
import com.nbp.currency.viewer.core.ui.UiResult
import com.nbp.currency.viewer.feature.currencyList.repository.AllCurrenciesNetworkRepository
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AllCurrenciesNetworkRepositoryTest {

    private lateinit var systemUnderTest: AllCurrenciesNetworkRepository
    private val mockGetAllNbpCurrencies: GetAllNbpCurrenciesUseCase = mock {
        onBlocking { invoke() } doReturn Result.Success(emptyList())
    }

    @Before
    fun setUp() {
        systemUnderTest = AllCurrenciesNetworkRepository(
            getAllNbpCurrencies = mockGetAllNbpCurrencies
        )
    }

    @Test
    fun `return error ui result`() = runTest {
        whenever(mockGetAllNbpCurrencies.invoke()).doReturn(Result.Failure(
            throwable = Throwable(message = "")
        ))
        val data = systemUnderTest.getAllCurrencies()

        Assertions.assertThat(data).isInstanceOfAny(UiResult.Error::class.java)
    }

    @Test
    fun `map network model to domain`() = runTest {
        val dummyData = getDummyNetworkResponse()
        whenever(mockGetAllNbpCurrencies.invoke()).doReturn(Result.Success(listOf(dummyData)))

        val data = systemUnderTest.getAllCurrencies()

        data as UiResult.Success
        Assertions.assertThat(data.data.rates).hasSize(4)
        Assertions.assertThat(data.data.rates?.get(0)?.currency).isEqualTo("Dolar amerykański")
        Assertions.assertThat(data.data.rates?.get(0)?.code).isEqualTo("USD")
        Assertions.assertThat(data.data.rates?.get(0)?.mid).isEqualTo(4.0898)
        Assertions.assertThat(data.data.rates?.get(1)?.currency).isEqualTo("Dolar Hongkongu")
        Assertions.assertThat(data.data.rates?.get(1)?.code).isEqualTo("HKD")
        Assertions.assertThat(data.data.rates?.get(1)?.mid).isEqualTo(0.5004)
        Assertions.assertThat(data.data.rates?.get(2)?.currency).isEqualTo("Dolar kanadyjski")
        Assertions.assertThat(data.data.rates?.get(2)?.code).isEqualTo("")
        Assertions.assertThat(data.data.rates?.get(2)?.mid).isEqualTo(0.0)
        Assertions.assertThat(data.data.rates?.get(3)?.currency).isEqualTo("")
        Assertions.assertThat(data.data.rates?.get(3)?.code).isEqualTo("")
        Assertions.assertThat(data.data.rates?.get(3)?.mid).isEqualTo(0.0)

    }

    private fun getDummyNetworkResponse() = NbpCurrencyTableAResponse(
        table = "A",
        no = "2137",
        effectiveDate = "2024-11-15",
        rates = listOf(
            NbpCurrencyRatesResponse(
                currency = "dolar amerykański",
                code = "USD",
                mid = 4.0898
            ),
            NbpCurrencyRatesResponse(
                currency = "dolar Hongkongu",
                code = "HKD",
                mid = 0.5004
            ),
            NbpCurrencyRatesResponse(
                currency = "dolar kanadyjski",
                code = null,
                mid = null
            ),
            NbpCurrencyRatesResponse(
                currency = null,
                code = null,
                mid = null
            ),
        )
    )
}