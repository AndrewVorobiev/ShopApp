package com.example.shopapp.data

import com.example.shopapp.domain.ShopItem


class ShopListMapper {

    // Этот метод будет преобразовывать сущность Domain слоя в модель базы данных
    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )
    // Этот метод будет делать обратное преобразование
    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled
    )

    // Этот метод будет делать преобразовывать  ListShopItemDbModel в ListShopItem
    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
