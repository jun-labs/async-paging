package project.io.app.common.codeandmessage

enum class CommonCodeAndMessage(
    val code: Int,
    val message: String,
) : CodeAndMessage {
    OK(200, "OK") {
        override fun code(): Int {
            return code
        }

        override fun message(): String {
            return message
        }
    },
    INVALID_PARAMETER(400, "올바르지 않은 파라미터입니다.") {
        override fun code(): Int {
            return code
        }

        override fun message(): String {
            return message
        }
    },
    INVALID_URL(404, "올바르지 않은 URL 입니다.") {
        override fun code(): Int {
            return code
        }

        override fun message(): String {
            return message
        }
    },
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류입니다.") {
        override fun code(): Int {
            return code
        }

        override fun message(): String {
            return message
        }
    },

    BAD_GATEWAY(502, "외부와의 통신에 실패했습니다.") {
        override fun code(): Int {
            return code
        }

        override fun message(): String {
            return message
        }
    }
}
