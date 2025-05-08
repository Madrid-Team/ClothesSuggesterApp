package data.repositories

import data.remote.datasource.location.LocationDataSource
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach

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

}