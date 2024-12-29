package com.coral.fithub.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*

@Entity(
    tableName = "Ejercicio"
)
data class Ejercicio (
    @PrimaryKey(autoGenerate = true)
    val idEjercicio: Int,

    val mejorMarca: Float?,

    val nombre: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idEjercicio)
        parcel.writeValue(mejorMarca)
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ejercicio> {
        override fun createFromParcel(parcel: Parcel): Ejercicio {
            return Ejercicio(parcel)
        }

        override fun newArray(size: Int): Array<Ejercicio?> {
            return arrayOfNulls(size)
        }
    }
}