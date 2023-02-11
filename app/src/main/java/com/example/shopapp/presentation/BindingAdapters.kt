package com.example.shopapp.presentation

import androidx.databinding.BindingAdapter
import com.example.shopapp.R
import com.google.android.material.textfield.TextInputLayout



// Этот файл создается для

// Пишем анотацию @BindingAdapter("errorInputName") -
// в скобках мы указываем имя атрибута, который будем использовать в XML файле
@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = message
}
