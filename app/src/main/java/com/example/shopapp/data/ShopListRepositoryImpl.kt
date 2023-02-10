package com.example.shopapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.domain.ShopItem
import com.example.shopapp.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    //sortedSetOf - позволяет сделать сортированный список, используя метод compareTo,
    // где мы один объект сравниваем по опрделенному параметру, в нашем случае по ID
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    // Переменная которая хранит id
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        // Условие говорит о том, что если ID элемента равен неопределенному ID,
        // то мы присвоем ему id, в противном случае мы ничего не будем делать,
        // и элемент с существующим ID добавится в коллекцию.
        // Это необходимо, чтобы не возникала ошибка при редактировании
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopList.remove(oldItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw java.lang.RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}