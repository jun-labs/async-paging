package project.io.app.common.configuration.data

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserWriteRepository
import project.io.app.logger

@Component
class DataInitializer(
    private val userRepository: UserWriteRepository,
) : ApplicationRunner {

    val log = logger()

    @Transactional
    override fun run(args: ApplicationArguments?) {
        val users = mutableListOf<User>()
        for (idx in 1..1_000_000) {
            users.add(User(idx.toString()))
        }
        log.info("users: {}", users.size)
        userRepository.saveAll(users)
    }
}
