package domain.usecases.location

import domain.models.location.LocationModel
import domain.repositories.LocationRepository
import domain.utils.exceptions.LocationException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetCurrentLocationUseCaseTest {
    private lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
    private lateinit var locationRepository: LocationRepository
    private lateinit var testScope: TestScope

    @BeforeEach
    fun setUp() {
        locationRepository = mockk(relaxed = true)
        getCurrentLocationUseCase = GetCurrentLocationUseCase(locationRepository)
        testScope = TestScope()
    }

    @Test
    fun `should return location model when pass a valid IP Address`() {
        testScope.runTest {
            //Given
            val ipAddress = "8.8.8.8"
            val expectedLocation = LocationModel(
                ip = "8.8.8.8",
                city = "Mountain View",
                countryName = "United States",
                latitude = 37.386,
                longitude = -122.0838,
                region = "California"
            )
            coEvery { locationRepository.getCurrentLocation(ipAddress) } returns expectedLocation
            // When
            val result = getCurrentLocationUseCase.getCurrentLocation(ipAddress)

            // Then
            assertEquals(expectedLocation, result)
        }

    }

    @Test
    fun `should throw exception when pass an invalid iP address`() {
        testScope.runTest {
            //Given
            val ipAddress = "abc.def.ghi"
            coEvery { locationRepository.getCurrentLocation(ipAddress) } throws LocationException.InvalidIpAddressException()

            // when && then
            assertThrows<LocationException.InvalidIpAddressException> {
                getCurrentLocationUseCase.getCurrentLocation(ipAddress)
            }
        }

    }

    @Test
    fun `should throw exception when pass an empty iP address`() {
        testScope.runTest {
            //Given
            val ipAddress = " "
            coEvery { locationRepository.getCurrentLocation(ipAddress) } throws LocationException.InvalidIpAddressException()

            // when && then
            assertThrows<LocationException.InvalidIpAddressException> {
                getCurrentLocationUseCase.getCurrentLocation(ipAddress)
            }
        }
    }


}