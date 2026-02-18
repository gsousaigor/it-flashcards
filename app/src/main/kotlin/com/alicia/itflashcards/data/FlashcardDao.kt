package com.alicia.itflashcards.data

import androidx.room.*
import com.alicia.itflashcards.model.Flashcard
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcards")
    fun getAllCards(): Flow<List<Flashcard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Flashcard)

    @Delete
    suspend fun deleteCard(card: Flashcard)

    @Update
    suspend fun updateCard(card: Flashcard)
}
