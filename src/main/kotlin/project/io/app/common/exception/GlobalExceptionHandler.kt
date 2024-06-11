import project.io.app.common.exception.InvalidParameterException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import project.io.app.common.codeandmessage.CommonCodeAndMessage
import project.io.app.common.exception.BusinessException
import project.io.app.logger

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = logger()

    @ExceptionHandler(NoHandlerFoundException::class)
    internal fun resolveIndexTypeException(
        ex: NoHandlerFoundException,
    ): ResponseEntity<ErrorResponse> {
        val codeAndMessage = CommonCodeAndMessage.INVALID_URL
        return ResponseEntity.status(codeAndMessage.code())
            .body(ErrorResponse(codeAndMessage.code(), codeAndMessage.message()))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    internal fun resolveMissingServletRequestParameterException(
        ex: MissingServletRequestParameterException,
    ): ResponseEntity<ErrorResponse> {
        val codeAndMessage = CommonCodeAndMessage.INVALID_PARAMETER
        log.error("errors:${ex.message}")
        return ResponseEntity.status(codeAndMessage.code())
            .body(ErrorResponse(codeAndMessage.code(), codeAndMessage.message()))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    internal fun resolveMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
    ): ResponseEntity<ErrorResponse> {
        val codeAndMessage = CommonCodeAndMessage.INVALID_PARAMETER

        val errors = HashMap<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Invalid input"
        }
        log.error("errors:${errors}")
        return ResponseEntity.status(codeAndMessage.code())
            .body(ErrorResponse(codeAndMessage.code(), codeAndMessage.message()))
    }

    @ExceptionHandler(InvalidParameterException::class)
    internal fun resolveInvalidParameterException(
        ex: InvalidParameterException,
    ): ResponseEntity<ErrorResponse> {
        val codeAndMessage = ex.codeAndMessage
        log.error("error:${ex.message}")
        return ResponseEntity.status(codeAndMessage.code())
            .body(ErrorResponse(codeAndMessage.code(), codeAndMessage.message()))
    }

    @ExceptionHandler(Exception::class)
    internal fun resolveException(
        ex: Exception,
    ): ResponseEntity<ErrorResponse> {
        val codeAndMessage = CommonCodeAndMessage.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(codeAndMessage.code)
            .body(ErrorResponse(codeAndMessage.code, codeAndMessage.message))
    }
}

internal class ErrorResponse(
    val code: Int,
    val message: String,
)
