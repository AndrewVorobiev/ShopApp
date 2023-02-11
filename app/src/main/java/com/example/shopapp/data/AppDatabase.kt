package com.example.shopapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



// Анотация @Database - объявляем все таблицы, которые будут использоваться в базе данных,
@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)

// База данных должна быть абстрактным классом и наследоваться от класса RoomDatabase
abstract class AppDatabase: RoomDatabase() {


    // добавляю абстрактную функцию, которая возвращает экземпляр нашего Dao
    abstract fun shopListDao(): ShopListDao

    companion object {

        // Переменная экземпляра базы данных
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "shop_item.db"


        // ------------------------------------------------------------------
        // Создаю метод getInstance, в котором передаю (Application),
        // И возвращаю AppDatabase
        // ------------------------------------------------------------------
        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it

                // ------------------------------------------------------------------
                // Данным способом проверяю,
                // если в этой переменной уже присвоенно значение, то её возвращаем
                // ------------------------------------------------------------------


            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it


                    // ------------------------------------------------------------------
                    // Такой проверкой синхронизирую два Синглотона. Это нужно для того,
                    // если два потока одновременно вызвали метод INSTANCE
                    // и проверили, что переменная равна Null,
                    // то оба потока дойдут до этой строчки
                    // и какой-то из них зайдет первым в этот блок
                    // Если не сделать данную проверку, то в переменной INSTANCE
                    // Будет присвоенно новое значение,
                    // тем самым будет два разных значения
                    // Такая реализация Синглтона называется - Double Check
                    // ------------------------------------------------------------------

                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME

                    // ------------------------------------------------------------------
                    // Создаю экземпляр базы данных, если он равен Null
                    // Вызывая Room.databaseBuilder
                    // пеередаю context - application
                    // и название базы данных - заранее вынесенная в константу
                    // ------------------------------------------------------------------


                )
//                    .allowMainThreadQueries() // Добавляет разрешение использовать главный поток
                    .build()
                INSTANCE = db //Переменной INSTANCE присваиваем полученное значение
                return db

                // И возвращаю значение db, потому что INSTANCE - нуллабельное
                // Иначе компилятор будет ругаться,
                // так как изначально возвращаю не нулабельный тип
            }
        }
    }
}
