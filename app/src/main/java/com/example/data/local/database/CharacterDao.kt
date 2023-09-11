package com.example.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.models.CharacterDbModel

@Dao
interface CharacterDao {

    @Query("SELECT * FROM favourite_character ORDER BY id DESC")
     fun getAllCharacters(): LiveData<List<CharacterDbModel>>

    @Query("SELECT * FROM favourite_character WHERE id = :id")
     fun getCharacterById(id: Int): LiveData<CharacterDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(cocktail: CharacterDbModel)

    @Query("DELETE FROM favourite_character WHERE id = :id")
    suspend fun deleteCharacter(id: Int)

}