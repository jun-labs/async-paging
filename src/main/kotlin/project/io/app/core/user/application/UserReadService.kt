package project.io.app.core.user.application

import org.springframework.data.domain.Pageable
import project.io.app.core.user.domain.User

interface UserReadService {
    suspend fun findUsers(pageable: Pageable): Pair<Long, List<User>>
}
