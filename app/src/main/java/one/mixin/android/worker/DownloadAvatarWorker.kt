package one.mixin.android.worker

import android.content.Context
import androidx.work.WorkerParameters
import androidx.work.Result
import com.bumptech.glide.Glide
import one.mixin.android.api.LocalJobException
import one.mixin.android.vo.User
import java.util.concurrent.TimeUnit

class DownloadAvatarWorker(context: Context, parameters: WorkerParameters) : AvatarWorker(context, parameters) {

    override fun onRun(): Result {
        val groupId = inputData.getString(GROUP_ID) ?: return Result.failure()
        val triple = checkGroupAvatar(groupId)
        if (triple.first) {
            return Result.success()
        }
        try {
            downloadBitmaps(users)
        } catch (e: Exception) {
            throw LocalJobException()
        }
        return Result.success()
    }

    private fun downloadBitmaps(users: MutableList<User>) {
        for (i in 0 until users.size) {
            val item = users[i].avatarUrl
            if (!item.isNullOrEmpty()) {
                Glide.with(applicationContext)
                    .asBitmap()
                    .load(item)
                    .submit()
                    .get(20, TimeUnit.SECONDS)
            }
        }
    }
}