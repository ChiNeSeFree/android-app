package one.mixin.android.crypto.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ColumnInfo.BLOB
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import org.whispersystems.libsignal.IdentityKey
import org.whispersystems.libsignal.IdentityKeyPair
import org.whispersystems.libsignal.ecc.Curve

@Entity(tableName = "identities", indices = [(Index(value = ["address"], unique = true))])
class Identity(
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "registration_id")
    val registrationId: Int?,
    @ColumnInfo(name = "public_key", typeAffinity = BLOB)
    val publicKey: ByteArray,
    @ColumnInfo(name = "private_key", typeAffinity = BLOB)
    val privateKey: ByteArray?,
    @ColumnInfo(name = "next_prekey_id")
    val nextPreKeyId: Long?,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    fun getIdentityKeyPair(): IdentityKeyPair {
        val publicKey = IdentityKey(publicKey, 0)
        val privateKey = Curve.decodePrivatePoint(privateKey)
        return IdentityKeyPair(publicKey, privateKey)
    }

    fun getIdentityKey() = IdentityKey(publicKey, 0)
}