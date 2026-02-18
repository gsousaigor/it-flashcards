package com.alicia.itflashcards.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String,
    val answer: String,
    val category: String,
    val isKnown: Boolean = false // Para o sistema de "Acertei/Errei"
)

object SampleData {
    val cards = listOf(
        Flashcard(question = "O que é o Docker?", answer = "Uma plataforma de código aberto que automatiza a implantação, o dimensionamento e o gerenciamento de aplicativos em contêineres.", category = "Infra/Cloud"),
        Flashcard(question = "Qual a diferença entre '==' e 'is' em Python?", answer = "'==' compara valores (igualdade), enquanto 'is' compara identidades de objetos (se são o mesmo objeto na memória).", category = "Programação"),
        Flashcard(question = "O que significa o acrônimo ACID em bancos de dados?", answer = "Atomicidade, Consistência, Isolamento e Durabilidade.", category = "TI/Dados"),
        Flashcard(question = "O que é um 'Pod' no Kubernetes?", answer = "A menor unidade de computação implantável que você pode criar e gerenciar no Kubernetes, consistindo de um ou mais contêineres.", category = "Cloud"),
        Flashcard(question = "O que é Injeção de Dependência?", answer = "Um padrão de design onde um objeto recebe outras instâncias de objetos de que depende, em vez de criá-los internamente.", category = "Engenharia de Software")
    )
}
