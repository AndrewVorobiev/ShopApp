package com.example.shopapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shopapp.domain.ShopItem
import com.example.shopapp.domain.ShopListRepository


// Данный класс реализует ShopListRepository, а также класс Dao.
// Чтобы правильно работало передаю контекст в виде Application
class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    // Создаю переменную, где объявляю  базу данных с методом shopListDao
    private val shopListDao = AppDatabase.getInstance(application).shopListDao()

    // Добавляем ShopListMapper для использования в методах
    private val mapper = ShopListMapper()


    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        // Потому что в Dao реализованно так, что addShopItem, не тоько добавляет, но и заменяет
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        // Получаем объект dbModel в котором получаем объект из базы
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }


    // 1 Вариант - Transformations.map. Передаем коллекцию из базы данных
    // и преобразовываем её в таблицу. Это тоже самое что и MediatorLiveData. Но прощец
    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}






            // 2 Вариант

            // ------------------------------------------------------------------
            //    override fun getShopList(): LiveData<List<ShopItem>> =
            //    MediatorLiveData<List<ShopItem>>().apply {
            //          addSource(shopListDao.getShopList()) {
            //              value = mapper.mapListDbModelToListEntity(it)
            // ------------------------------------------------------------------

            // Создаем экземпляр класса MediatorLiveData - он помогает связать данные между LiveData

            // Первым делом нужно указать ему источник данных, объекты какой LiveData
            // Он будет принимать. После мы можем установить значение через метод setValue

            // addSource(shopListDao.getShopList()) - возвращает объект LiveData, в
            // этом объекте лежит коллекция элементов dbmodel,
            // которую мы не можем передать в другме слои
            // Поэтому нам необходимо каким-то образов перехватывать эти данные, чтобы получать
            // обновления данных на экране для этого существует MediatorLiveData

            //

