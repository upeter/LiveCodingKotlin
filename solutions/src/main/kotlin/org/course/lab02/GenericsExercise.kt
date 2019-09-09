package org.course.lab02

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty

/**
 * Open: GenericsExercise.kt and GenericsExerciseTest
 * Exercise 1a:
 * In GenericsExercise familiarize yourself with the domain classes  @{link Animal},  @{link Dog},  @{link Printer},  @{link AnimalPrinter}.
 * Introduce generics in the Printer class so that test Exercise 1a in GenericsExerciseTest succeeds.
 *
 * Exercise 1b:
 * Uncomment test Exercise 1b in GenericsExerciseTest. This test will not compile.
 * To make it work add the correct variance annotation to the generic type of @{link Printer}.
 *
 * Exercise 2:
 * In GenericsExercise.kt familiarize yourself with the domain classes  @{link AnimalPlace} and  @{link DogHouse}.
 * Uncomment test Exercise 2 in GenericsExerciseTest. This test will not compile.
 * To make it work add the correct variance annotation to the generic type of @{link AnimalPlace}. Can you see the difference with the previous exercise?
 *
 * Exercise 3:
 * In GenericsExercise.kt familiarize yourself with the domain classes  @{link Document},  @{link XmlDoc},  @{link JsonDoc},  @{link Transformer},  @{link AnimalToJsonTransformer},  @{link AnimalToXmlTransformer} and  @{link DogToDocumentTransformer}.
 * Uncomment test Exercise 3 in GenericsExerciseTest. This test will not compile. What do you need to do to make it compile?
 */


/*------------------
* Animal domain (Exercise 1a, 1b, 2, 3)
* ------------------
*/
open class Animal(val name: String) {
    val type = this::class.java.simpleName
}

class Dog(name: String) : Animal(name) {
    fun barf() = println("$type with name $name says: barf")
}


/*------------------
* Print domain (Exercise 1a, 1b)
* ------------------
*/
abstract class Printer<in T> {
    abstract fun print(input: T): Unit
}

class AnimalPrinter : Printer<Animal>() {
    override fun print(animal: Animal) = println("The ${animal.type} is called ${animal.name}")
}

/*------------------
* Animalplace domain (Exercise 2)
* ------------------
*/
open class AnimalPlace<out T : Animal>(val animal: T)

class DogHouse(dog: Dog) : AnimalPlace<Dog>(dog) {
    fun serialize(f: Transformer<Dog, Document>) = f.transform(animal)
}


/*------------------
* Document domain (json/xml) (Exercise 3)
* ------------------
*/
abstract class Document(open val tag: String, open val content: String)

data class XmlDoc(override val tag: String, override val content: String) : Document(tag, content) {
    override fun toString(): String = """<$tag>$content</$tag>"""
}

data class JsonDoc(override val tag: String, override val content: String) : Document(tag, content) {
    override fun toString(): String = """{"$tag":"$content"}"""
}


/*------------------
* Transformation domain (Exercise 3)
* ------------------
*/
interface Transformer<in I, out O> {
    fun transform(i: I): O
}

class AnimalToJsonTransformer : Transformer<Animal, JsonDoc> {
    override fun transform(i: Animal): JsonDoc = JsonDoc(i.type, i.name)
}

class AnimalToXmlTransformer : Transformer<Animal, XmlDoc> {
    override fun transform(i: Animal): XmlDoc = XmlDoc(i.type, i.name)
}

class DogToDocumentTransformer : Transformer<Dog, Document> {
    override fun transform(i: Dog) = XmlDoc(i.type, i.name)
}
