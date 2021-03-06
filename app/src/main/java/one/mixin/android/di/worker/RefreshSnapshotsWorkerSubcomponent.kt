package one.mixin.android.di.worker

import dagger.Subcomponent
import dagger.android.AndroidInjector
import one.mixin.android.worker.RefreshSnapshotsWorker

@Subcomponent
interface RefreshSnapshotsWorkerSubcomponent : AndroidInjector<RefreshSnapshotsWorker> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RefreshSnapshotsWorker>()
}