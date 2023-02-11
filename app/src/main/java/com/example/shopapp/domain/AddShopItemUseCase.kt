package com.example.shopapp.domain


// Это Use-case - это оболочка функции, которая будет использоваться в ShopListRepository.kt
// Но логика такая, мы сначала создаем этот класс, пишем функцию,
// после объявляем в Репозитории и наследуемся
class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}