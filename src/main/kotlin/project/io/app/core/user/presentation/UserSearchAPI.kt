package project.io.app.core.user.presentation

import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.io.app.common.response.ApiResponse
import project.io.app.core.user.application.UserReadService
import project.io.app.core.user.domain.User
import project.io.app.logger

@RestController
@RequestMapping("/api/users")
class UserSearchAPI(
    private val userService: UserReadService,
) {

    private val log = logger()

    @GetMapping
    suspend fun searchUsers(@PageableDefault pageable: Pageable): ApiResponse<UserSearchResponse> {
        log.info("pageable=$pageable")
        val result = userService.findUsers(pageable)
        val data = result.second.map { UserResponse(it) }
            .toList()

        val payload = UserSearchResponse(
            pageable = pageable,
            totalCount = result.first,
            users = data
        )
        return ApiResponse.ok(payload)
    }
}

data class UserResponse(
    private val user: User,
) {
    val id = user.id
    val name = user.name
}

data class UserSearchResponse(
    private val pageable: Pageable,
    val totalCount: Long,
    val users: List<UserResponse>,
) {
    val size = pageable.pageSize
    val page = pageable.pageNumber
    val sorted = pageable.sort.isSorted
}
