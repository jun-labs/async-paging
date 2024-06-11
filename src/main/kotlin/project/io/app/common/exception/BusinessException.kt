package project.io.app.common.exception

import project.io.app.common.codeandmessage.CodeAndMessage

open class BusinessException(
    open val codeAndMessage: CodeAndMessage,
) : RuntimeException(
    codeAndMessage.message()
)
