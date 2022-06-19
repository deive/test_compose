package uk.rigly.deive.testcompose.address.details

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uk.rigly.deive.testcompose.address.list.AddressItem
import java.util.*

/** Address table. */
@Dao
interface AddressDao {
    @Query("SELECT * FROM address WHERE id = :id")
    fun getById(id: UUID): Flow<Address?>

    @Query("SELECT * FROM address")
    fun getAll(): Flow<List<AddressItem>>

    @Insert
    suspend fun insertAll(data: List<Address>)
    @Insert
    suspend fun insert(data: Address)
    @Update
    suspend fun update(data: Address)
    @Delete
    suspend fun delete(data: Address)

}
