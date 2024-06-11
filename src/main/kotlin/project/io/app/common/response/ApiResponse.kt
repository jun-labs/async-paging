package project.io.app.common.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.time.LocalDateTime.now

class ApiResponse<T>(
    status: HttpStatus,
    data: T?,
) : ResponseEntity<PayLoad<T>>(PayLoad(data, status, now()), status) {

    companion object {
        fun <T> ok(data: T?): ApiResponse<T> {
            return ApiResponse(HttpStatus.OK, data)
        }
    }
}

class PayLoad<T>(
    val data: T?,
    private val status: HttpStatus,
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val eventTime: LocalDateTime? = now(),
) {
    val code = status.value()
    val message = status.name
}
