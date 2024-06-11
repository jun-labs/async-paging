package project.io.app.test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import project.io.app.common.configuration.data.DataInitializer
import project.io.app.core.user.persistence.UserWriteRepository

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase {

    @Autowired
    protected lateinit var userWriteRepository: UserWriteRepository

    @MockBean
    protected lateinit var dataInitializer: DataInitializer

    @BeforeEach
    fun setUp() {
        userWriteRepository.clear()
    }
}
