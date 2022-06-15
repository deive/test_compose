package uk.rigly.deive.testcompose

import androidx.room.*
import androidx.room.Database
import uk.rigly.deive.testcompose.address.details.Address
import uk.rigly.deive.testcompose.address.details.AddressDao

@Database(entities = [Address::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}

//interface BaseDao<T> {
//    @Transaction
//    suspend fun upsert(data: List<T>) {
//        val insertResult = insert(data)
//        val updateList: MutableList<T> = ArrayList()
//
//        for (i in insertResult.indices) {
//            if (insertResult[i] == -1) {
//                updateList.add(data[i])
//            }
//        }
//
//        if (updateList.isNotEmpty()) {
//            update(updateList)
//        }
//    }
//
//    @Transaction
//    suspend fun upsert(data: T) {
//        val id = insert(data)
//        if (id == -1) {
//            update(data)
//        }
//    }
//
//    @Insert
//    suspend fun insert(data: List<T>): List<Int>
//    @Update
//    suspend fun update(data: List<T>)
//}
