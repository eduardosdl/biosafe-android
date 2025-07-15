package com.eduardosdl.biosafe.domain.usecase

import com.eduardosdl.biosafe.R
import com.eduardosdl.biosafe.domain.exceptions.RequestException
import com.eduardosdl.biosafe.domain.exceptions.RequestExceptionType
import com.eduardosdl.biosafe.domain.exceptions.ValidationExceptionType
import com.eduardosdl.biosafe.domain.model.User
import com.eduardosdl.biosafe.domain.repository.UserRepository
import com.eduardosdl.biosafe.domain.utils.AppResult
import kotlinx.io.IOException

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getAll(): AppResult<List<User>> {
        return try {
            AppResult.Success(userRepository.getAll())
        } catch (e: IOException) {
            AppResult.Failure(R.string.error_network)
        } catch (e: Exception) {
            AppResult.Failure(R.string.unknown_error)
        }
    }

    suspend fun create(name: String): AppResult<Unit> {
        return try {
            if (name.isEmpty()) {
                return AppResult.ValidationFailure(ValidationExceptionType.EMPTY)
            }

            AppResult.Success(userRepository.create(name))
        } catch (e: RequestException) {
            if (e.type == RequestExceptionType.CLIENT) {
                return AppResult.ValidationFailure(ValidationExceptionType.ALREADY_EXISTS)
            }

            AppResult.Failure(R.string.unknown_error)
        } catch (e: IOException) {
            AppResult.Failure(R.string.error_network)
        } catch (e: Exception) {
            AppResult.Failure(R.string.unknown_error)
        }
    }
}