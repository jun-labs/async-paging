package project.io.app.core.user.persistence.repository

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserWriteRepository
import project.io.app.logger

@Repository
class UserEntityWriteRepository(
    private val entityManager: EntityManager,
) : UserWriteRepository {

    private val log = logger()

    override fun saveAll(users: List<User>) {
        users.forEach { user ->
            entityManager.persist(user)
        }
        entityManager.flush()
        log.info("FINISHED")
    }
}
