package com.example.shopapp.domain

// Здесь создаем экземпляр класса, который будет использоваться в бизнес-логике
data class ShopItem(

    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID

) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

