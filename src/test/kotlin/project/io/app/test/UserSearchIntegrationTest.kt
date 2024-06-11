package project.io.app.test

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import project.io.app.core.user.application.UserReadService
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserWriteRepository

class UserSearchIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var userWriteRepository: UserWriteRepository

    @Autowired
    private lateinit var userReadService: UserReadService

    @Test
    fun whenSearchUsersWithPageableThenResultShouldBeNotEmpty(): Unit = runBlocking {
        val users = mutableListOf<User>()
        for (idx in 1..1_000) {
            users.add(User(idx.toString()))
        }
        userWriteRepository.saveAll(users)

        val findUsers = userReadService.findUsers(PageRequest.of(0, 10))
        assertTrue(findUsers.first.toInt() == 1_000)
        assertTrue(findUsers.second.size == 10)
    }
}
