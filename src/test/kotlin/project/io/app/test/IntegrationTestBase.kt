package project.io.app.test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import project.io.app.common.configuration.data.DataInitializer

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase {

    @MockBean
    protected lateinit var dataInitializer: DataInitializer
}
