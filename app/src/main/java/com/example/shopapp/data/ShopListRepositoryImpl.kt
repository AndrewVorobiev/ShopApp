package com.example.shopapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shopapp.domain.ShopItem
import com.example.shopapp.domain.ShopListRepository


// Данный класс реализует ShopListRepository, а также класс Dao.
// Чтобы правильно работало передаю контекст в виде Application
class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    // Создаю переменную, где объявляю  базу данных с методом shopListDao
    private val shopListDao = AppDatabase.getInstance(application).shopListDao()

    // Добавляем ShopListMapper для использования в методах
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        // Потому что в Dao реализованно так, что addShopItem, не тоько добавляет, но и заменяет
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}
