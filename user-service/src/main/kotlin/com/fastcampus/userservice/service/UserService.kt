package com.fastcampus.userservice.service

import com.fastcampus.userservice.config.JWTProperties
import com.fastcampus.userservice.domain.entity.User
import com.fastcampus.userservice.domain.repository.UserRepository
import com.fastcampus.userservice.exception.PasswordNotMatchedException
import com.fastcampus.userservice.exception.UserNotFoundException
import com.fastcampus.userservice.model.SignUpRequest
import com.fastcampus.userservice.utils.BCryptUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProperties: JWTProperties,
) {

    suspend fun signUp(signUpRequest: SignUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserNotFoundException()
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username
            )
            userRepository.save(user)
        }
    }
}