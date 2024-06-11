package project.io.app.test

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserReadRepository
import project.io.app.core.user.persistence.UserWriteRepository

class UserSaveIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var userWriteRepository: UserWriteRepository

    @Autowired
    private lateinit var userReadRepository: UserReadRepository

    @Test
    fun whenSaveUserThenIdShouldBeNotNull(): Unit = runBlocking {
        val users = mutableListOf(User("Hello World"))
        userWriteRepository.saveAll(users)

        val findUsers = userReadRepository.findUsers(0, 10)
        assertTrue(findUsers.first > 0)
    }
}
