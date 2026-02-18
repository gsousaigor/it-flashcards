package com.alicia.itflashcards.model

data class Flashcard(
    val id: Int,
    val question: String,
    val answer: String,
    val category: String
)

object SampleData {
    val cards = listOf(
        Flashcard(1, "O que é o Docker?", "Uma plataforma de código aberto que automatiza a implantação, o dimensionamento e o gerenciamento de aplicativos em contêineres.", "Infra/Cloud"),
        Flashcard(2, "Qual a diferença entre '==' e 'is' em Python?", "'==' compara valores (igualdade), enquanto 'is' compara identidades de objetos (se são o mesmo objeto na memória).", "Programação"),
        Flashcard(3, "O que significa o acrônimo ACID em bancos de dados?", "Atomicidade, Consistência, Isolamento e Durabilidade.", "TI/Dados"),
        Flashcard(4, "O que é um 'Pod' no Kubernetes?", "A menor unidade de computação implantável que você pode criar e gerenciar no Kubernetes, consistindo de um ou mais contêineres.", "Cloud"),
        Flashcard(5, "O que é Injeção de Dependência?", "Um padrão de design onde um objeto recebe outras instâncias de objetos de que depende, em vez de criá-los internamente.", "Engenharia de Software")
    )
}
