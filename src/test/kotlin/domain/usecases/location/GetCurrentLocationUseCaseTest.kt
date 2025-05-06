package domain.usecases.location

import domain.repositories.LocationRepository
import org.junit.jupiter.api.Assertions.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

 class GetCurrentLocationUseCaseTest{
  lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
  lateinit var locationRepository: LocationRepository
  @BeforeEach
  fun setUp() {
   locationRepository = mockk(relaxed = true)
   getCurrentLocationUseCase = GetCurrentLocationUseCase(locationRepository)
  }

 }