package org.course.lab03

import io.kotlintest.specs.WordSpec
import ch.qos.logback.classic.Logger as LogbackLogger

class GenericsExerciseTest : WordSpec() {
    init {

        "GenericsExerciseTest" should {
            "Exercise 1a: make Printer generic (Printer<T>) so that AnimalPrinter only accepts Animal objects" {
                val printer = AnimalPrinter()
                //should not compile
                printer.print("No animal")
                //should work
                printer.print(Dog("Fido"))

            }
            "Exercise 1b: make AnimalPrinter (Printer<Animal>) to be assignable to DogPrinter (Printer<Dog>))" {
                TODO("uncomment next lines")
//                val printer = AnimalPrinter()
//                val dogPrinter: Printer<Dog> = printer
//                dogPrinter.print(Dog("Fido"))
            }
            "Exercise 3: make DogHouse (AnimalPlace<Dog>) to be assignable to AnimalPlace (AnimalPlace<Animal>)" {
                TODO("uncomment next lines")
//                val dogHouse = DogHouse(Dog("Fido"))
//                val animalPlace: AnimalPlace<Animal> = dogHouse
            }
            "Exercise 4: make AnimalToJson/XmlTransformer (Animal->JsonDoc | Animal->XmlDoc) to be compatible with Dog->Document signature" {
                val dogHouse = DogHouse(Dog("Fido"))
                //works:
                dogHouse.serialize(DogToDocumentTransformer())
                TODO("uncomment next lines ")
//              dogHouse.serialize(AnimalToXmlTransformer())
//              dogHouse.serialize(AnimalToJsonTransformer())
            }
        }
    }
}
