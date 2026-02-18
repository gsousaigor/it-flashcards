package com.alicia.itflashcards.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alicia.itflashcards.data.AppDatabase
import com.alicia.itflashcards.model.Flashcard
import com.alicia.itflashcards.model.SampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FlashcardViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).flashcardDao()
    val allCards: Flow<List<Flashcard>> = dao.getAllCards()

    init {
        // Popula o banco com os dados iniciais caso esteja vazio
        viewModelScope.launch {
            if (dao.getAllCards().first().isEmpty()) {
                SampleData.cards.forEach { dao.insertCard(it) }
            }
        }
    }

    fun addCard(question: String, answer: String, category: String) {
        viewModelScope.launch {
            dao.insertCard(Flashcard(question = question, answer = answer, category = category))
        }
    }

    fun updateCardStatus(card: Flashcard, isKnown: Boolean) {
        viewModelScope.launch {
            dao.updateCard(card.copy(isKnown = isKnown))
        }
    }

    fun deleteCard(card: Flashcard) {
        viewModelScope.launch {
            dao.deleteCard(card)
        }
    }
}
