package com.dangrover.danrecipe
import android.content.Context
import androidx.room.*

@Database(entities = [Recipe::class, RecipeScheduleOccurrence::class],
    version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class RecipeAppDatabase : RoomDatabase() {

    // DAOs
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var DB_INSTANCE: RecipeAppDatabase? = null

        fun getDatabase(context: Context): RecipeAppDatabase {
            return DB_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeAppDatabase::class.java,
                    "recipes-database.db" // The name of your database file (will be created in the app's data directory)
                ).allowMainThreadQueries()
                    .createFromAsset("default-recipes-database.db") // The name of the file in assets/
                    .build()
                DB_INSTANCE = instance
                instance
            }
        }
    }
}