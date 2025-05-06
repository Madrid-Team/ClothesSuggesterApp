package domain.usecases.location

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.repositories.LocationRepository
import domain.utils.exceptions.ClothesExceptions
import domain.utils.exceptions.LocationExceptions
import io.mockk.coEvery
import org.junit.jupiter.api.Assertions.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class GetCurrentLocationUseCaseTest {
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
    lateinit var locationRepository: LocationRepository

    @BeforeEach
    fun setUp() {
        locationRepository = mockk(relaxed = true)
        getCurrentLocationUseCase = GetCurrentLocationUseCase(locationRepository)
    }

    @Test
    fun `should return location model when pass a valid IP Address`() {
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

    @Test
    fun `should throw exception when pass an invalid iP address`() {
        //Given
        val ipAddress = "abc.def.ghi"
        coEvery { locationRepository.getCurrentLocation(ipAddress) } throws LocationExceptions.InvalidIpAddressException()

        // when && then
        assertThrows<LocationExceptions.InvalidIpAddressException> {
            getCurrentLocationUseCase.getCurrentLocation(ipAddress)
        }
    }
    @Test
    fun `should throw exception when pass an empty iP address`() {
        //Given
        val ipAddress = " "
        coEvery { locationRepository.getCurrentLocation(ipAddress) } throws LocationExceptions.InvalidIpAddressException()

        // when && then
        assertThrows<LocationExceptions.InvalidIpAddressException> {
            getCurrentLocationUseCase.getCurrentLocation(ipAddress)
        }
    }

}