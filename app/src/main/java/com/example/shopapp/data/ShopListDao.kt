package com.example.shopapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// Создание интерфейса Dao - которое будет отвечать за работу базы данных (insert,delete и т.д)
@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    // onConflict = OnConflictStrategy.REPLACE - означает,
    // что если в базу данных будет добавлен новый элемент, то он будет заменен
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    // LIMIT 1 - Добавляется для того, чтобы по запросу возвращался только один элемент
    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel
}
