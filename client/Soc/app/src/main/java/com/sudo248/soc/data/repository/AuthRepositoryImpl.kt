package com.sudo248.soc.data.repository

import android.util.Log
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.exception.ApiException
import com.sudo248.base_android.ktx.state
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc.data.api.BaseResponse
import com.sudo248.soc.data.api.auth.AuthService
import com.sudo248.soc.data.api.auth.request.AccountRequest
import com.sudo248.soc.data.api.auth.request.ChangePasswordRequest
import com.sudo248.soc.data.api.auth.request.OtpRequest
import com.sudo248.soc.data.api.notification.NotificationService
import com.sudo248.soc.data.ktx.errorBody
import com.sudo248.soc.data.ktx.fromResponse
import com.sudo248.soc.data.mapper.toToken
import com.sudo248.soc.domain.common.Constants
import com.sudo248.soc.domain.entity.auth.Account
import com.sudo248.soc.domain.entity.auth.Token
import com.sudo248.soc.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:32 - 05/03/2023
 */

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val notificationService: NotificationService,
    private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun tryGetToken(): DataState<Token, Exception> = stateOn(ioDispatcher) {
        val token = SharedPreferenceUtils.withApplicationSharedPreference().getString(Constants.Key.TOKEN, "")
        if (token.isEmpty()) {
            throw Exception()
        } else {
            val response = handleResponse(authService.tryGetToken())
            if (response.isSuccess) {
                val token = response.get().data!!.toToken()
                saveToken(token.token)
                token
            } else {
                throw response.error().errorBody()
            }
        }
    }

    override suspend fun saveToken(token: String): DataState<Unit, Exception> = state {
        Log.d("Sudoo", "saveToken: $token")
        SharedPreferenceUtils.withApplicationSharedPreference()
            .putString(Constants.Key.TOKEN, token)
        val fcmToken = SharedPreferenceUtils.getString(Constants.Key.FCM_TOKEN, "")
        if (fcmToken.isNotEmpty()) {
            notificationService.saveToken(fcmToken)
        }
    }

    override suspend fun signIn(account: Account): DataState<Token, Exception> =
        stateOn(ioDispatcher) {
            val request = AccountRequest(
                phoneNumber = account.phoneNumber,
                password = account.password,
                provider = account.provider
            )
            val response = handleResponse(authService.signIn(request))
            if (response.isSuccess) {
                response.get().data!!.toToken()
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun signUp(account: Account): DataState<Unit, Exception> =
        stateOn(ioDispatcher) {
            val request = AccountRequest(
                phoneNumber = account.phoneNumber,
                password = account.password,
                provider = account.provider
            )
            val response = handleResponse(authService.signUp(request))
            if (response.isSuccess) {
                response.get().data?.let {
                    Log.d("Sudoo", "signUp: userId: $it")
                    SharedPreferenceUtils.putString(Constants.Key.USER_ID, it)
                }
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun generateOtp(phoneNumber: String): DataState<Unit, Exception> =
        stateOn(ioDispatcher) {
            val response = handleResponse(authService.generateOtp(phoneNumber))
            if (response.isError) {
                throw response.error().errorBody()
            }
        }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): DataState<Token, Exception> =
        stateOn(ioDispatcher) {
            val request = OtpRequest(
                phoneNumber,
                otp
            )
            val response = handleResponse(authService.verifyOtp(request))
            if (response.isSuccess) {
                response.get().data!!.toToken()
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun changePassword(
        oldPassword: String,
        newPassWord: String
    ): DataState<Unit, Exception> = stateOn(ioDispatcher) {
        val request = ChangePasswordRequest(
            oldPassword,
            newPassWord
        )
        val response = handleResponse(authService.changePassword(request))
        if (response.isError) {
            throw response.error().errorBody()
        }
    }

    override suspend fun logout(): DataState<Unit, Exception> = stateOn(ioDispatcher) {
        authService.logout()
    }
}