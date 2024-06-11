package project.io.app.core.user.persistence

import project.io.app.core.user.domain.User

interface UserWriteRepository {
    fun saveAll(users: List<User>)

    fun clear()
}
