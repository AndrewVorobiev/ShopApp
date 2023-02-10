package com.example.shopapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Анотация @Entity вызывается для объявления классов "Таблицой",
// в круглых скобках пишу название таблицы
@Entity(tableName = "shop_item")
data class ShopItemDbModel(

    // Анотация @PrimaryKey вызываетя для того, чтобы генерировать автоматический ID
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)