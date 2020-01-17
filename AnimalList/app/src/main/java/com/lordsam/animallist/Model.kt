package com.lordsam.animallist

data class Animal(var name :String)

object Supplier{
    val animals = listOf<Animal>(
        Animal("Lion"),
        Animal("Elephant"),
        Animal("Tiger"),
        Animal("Deer"),
        Animal("Wolf"),
        Animal("Fox"),
        Animal("Hippo"),
        Animal("Alligator"),
        Animal("Monkey"),
        Animal("Ostrich")
    )
}