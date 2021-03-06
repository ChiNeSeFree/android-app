package one.mixin.android.worker

import android.content.Context
import androidx.work.Result
import androidx.work.WorkerParameters
import one.mixin.android.api.service.AssetService
import one.mixin.android.db.AddressDao
import javax.inject.Inject

class RefreshAddressWorker(context: Context, parameters: WorkerParameters) : BaseWork(context, parameters) {

    companion object {
        const val ASSET_ID = "asset_id"
    }

    @Inject
    lateinit var assetService: AssetService

    @Inject
    lateinit var addressDao: AddressDao

    override fun onRun(): Result {
        val assetId = inputData.getString(ASSET_ID) ?: return Result.failure()
        val response = assetService.addresses(assetId).execute().body()
        return if (response != null && response.isSuccess && response.data != null) {
            response.data?.let {
                addressDao.insertList(it)
            }
            Result.success()
        } else {
            Result.failure()
        }
    }
}