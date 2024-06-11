package project.io.app.core.user.application.service

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import project.io.app.core.user.application.UserReadService
import project.io.app.core.user.domain.User
import project.io.app.core.user.domain.UserReadRepository
import project.io.app.logger
import java.lang.System.currentTimeMillis

@Service
class UserService(
    private val userReadRepository: UserReadRepository,
) : UserReadService {

    private val log = logger()

    override suspend fun findUsers(pageable: Pageable): Pair<Long, List<User>> {
        val startTime = currentTimeMillis()
        val result = userReadRepository.findUsers(pageable.pageNumber, pageable.pageSize)
        val endTime = currentTimeMillis()
        log.info("ExecutionTime: {}", (endTime - startTime) / 1_000.0)
        return result
    }
}
