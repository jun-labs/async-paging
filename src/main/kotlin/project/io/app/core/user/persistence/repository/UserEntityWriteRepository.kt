package project.io.app.core.user.persistence.repository

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserWriteRepository
import project.io.app.logger

/**
 * 테스트를 위한 클래스.
 * 실제 개발 환경이라면 @Transactional 제거할 것.
 * */
@Repository
class UserEntityWriteRepository(
    private val entityManager: EntityManager,
) : UserWriteRepository {

    private val log = logger()

    @Transactional
    override fun saveAll(users: List<User>) {
        users.forEach { user ->
            entityManager.persist(user)
        }
        entityManager.flush()
        log.info("FINISHED")
    }

    @Transactional
    override fun clear() {
        entityManager.createQuery("DELETE FROM users").executeUpdate();
    }
}
