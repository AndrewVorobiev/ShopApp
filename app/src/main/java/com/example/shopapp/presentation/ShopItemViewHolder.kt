package com.sumin.shoppinglist.presentation

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ShopItemViewHolder(

    // если бы у нас был только один типа Layout - то мы могли написать
    // ItemShopDisabledBinding - то есть передать разметку item_shop_disabled.xml
    // Но у нас два типа, поэтому образаемся к ViewDataBinding - это родительский класс
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)
