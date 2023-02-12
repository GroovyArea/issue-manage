package com.fastcampus.userservice.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message)

data class UserExistsException(
    override val message: String = "Already exists user."
) : ServerException(409, message)

data class UserNotFoundException(
    override val message: String = "User not found."
) : ServerException(404, message)

data class PasswordNotMatchedException(
    override val message: String = "Invalid password."
) : ServerException(400, message)

data class InvalidJwtTokenException(
    override val message: String = "Invalid token."
) : ServerException(400, message)