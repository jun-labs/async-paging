package project.io.app.common.exception

import project.io.app.common.codeandmessage.CodeAndMessage

class InvalidParameterException(
    val codeAndMessage: CodeAndMessage,
) : RuntimeException()
