package uk.rigly.deive.testcompose

import androidx.room.*
import androidx.room.Database
import uk.rigly.deive.testcompose.address.details.Address
import uk.rigly.deive.testcompose.address.details.AddressDao

@Database(entities = [Address::class], version = 1)
abstract class DatabaseProvider : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}
