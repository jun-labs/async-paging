package project.io.app.core.user.persistence

import project.io.app.core.user.domain.User

interface UserReadRepository {
    suspend fun findUsers(page: Int, size: Int): Pair<Long, List<User>>
}
