package project.io.app.core.user.persistence.repository

import com.querydsl.core.types.dsl.Expressions.numberTemplate
import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Repository
import project.io.app.core.user.domain.QUser.user
import project.io.app.core.user.domain.User
import project.io.app.core.user.persistence.UserReadRepository

@Repository
class UserEntityReadRepository(
    private val queryFactory: JPAQueryFactory,
) : UserReadRepository {

    companion object {
        private val totalCountExpression = numberTemplate(Long::class.java, "count(1)")
    }

    override suspend fun findUsers(
        page: Int,
        size: Int,
    ): Pair<Long, List<User>> = coroutineScope {
        val totalCount = async {
            queryFactory.select(totalCountExpression)
                .from(user)
                .fetchOne() ?: 0L
        }

        val findUsers = async {
            queryFactory.selectFrom(user)
                .offset((page) * size.toLong())
                .limit(10)
                .fetch()
        }
        Pair(totalCount.await(), findUsers.await())
    }
}
