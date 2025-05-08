package data.repositories

import data.remote.datasource.location.LocationDataSource
import domain.models.locationModels.LocationModel
import domain.utils.exceptions.LocationException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocationRepositoryImplTest{
  private lateinit var locationDataSource: LocationDataSource
  private lateinit var repository: LocationRepositoryImpl

  @BeforeEach
  fun setup() {
      locationDataSource = mockk(relaxed = true)
   repository = LocationRepositoryImpl(
       locationDataSource
   )
  }
    @Test
    fun `getCurrentLocation returns correct LocationModel when datasource succeeds`() = runTest {
        // Given
        val fakeResponse = createLocationResponseModel(
            ip = "192.168.1.1",
            city = "Cairo",
            countryName = "Egypt",
            latitude = 30.0444,
            longitude = 31.2357,
            region = "Cairo"
        )
        coEvery { locationDataSource.getCurrentLocation("192.168.1.1") } returns fakeResponse

        // When
        val result = repository.getCurrentLocation("192.168.1.1")

        // Then
        val expected = LocationModel(
            ip = "192.168.1.1",
            city = "Cairo",
            countryName = "Egypt",
            latitude = 30.0444,
            longitude = 31.2357,
            region = "Cairo"
        )

        assertEquals(expected, result)
    }
    @Test
    fun `getCurrentLocation throws LocationApiException when datasource fails`() = runTest {
        coEvery { locationDataSource.getCurrentLocation(any()) } throws RuntimeException("Network error")

        assertThrows<LocationException.LocationApiException> {
            repository.getCurrentLocation("invalid-ip")
        }
    }

}