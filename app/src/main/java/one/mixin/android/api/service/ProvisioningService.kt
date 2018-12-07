package one.mixin.android.api.service

import io.reactivex.Observable
import one.mixin.android.api.MixinResponse
import one.mixin.android.api.request.ProvisioningRequest
import one.mixin.android.api.response.ProvisioningResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProvisioningService {

    @GET("/device/provisioning/code")
    fun provisionCode(): Observable<MixinResponse<ProvisioningResponse>>

    @POST("/provisionings/{id}")
    fun updateProvisioning(@Path("id") id: String, @Body request: ProvisioningRequest): Observable<MixinResponse<ProvisioningResponse>>
}