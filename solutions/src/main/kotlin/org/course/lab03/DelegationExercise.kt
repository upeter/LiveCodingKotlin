package org.course.lab03

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegation Exercises
 * Open: DelegationExercise and DelegationExerciseTest
 * Exercise 1: Class Delegation
 * Take a look at the Company class. It merely wraps a list of Employees. Consequently, all properties and methods need to be accessed through the employee collection, like:
 * myCompany.employees.size. In addition, all calls to the logger are delegated to the logger field.
 * Enhance access to the employee collection and logger by means of class delegation, so that we can:
 * - access the fields and methods of the employee collection directly through the Company instance like: myCompany.size
 * - access the methods of the logger directly like this.info("hi")
 *
 * Exercise 2: Built in Property Delegates
 * The averageAge field in Company is always initialized, regardless whether it is accessed or not.
 * Change the initialization logic for averageAge to use the built-in lazy delegate. Don't forget the log statement when the field is initialized.
 *
 * Exercise 3: Custom Property Delegates
 * In this exercise we want to trace the Employee's age field access. First, take a look at the FieldAccessTracer companion object.
 * It contains a traceGetter and traceSetter method that keeps track of how many times a particular setter or getter is called.
 * Convert the FieldAccessTracer into a property delegate implementing the methods of the ReadWriteProperty interface. In the getValue(...) method
 * make use of traceGetter to track field access. In the setValue(...) method make use of traceSetter to track a field assignment.
 * Finally, apply the FieldAccessTracer delegate on the Employee's age and name field.
 * Make all tests succeed.
 */
class Company(private val employees: List<Employee>) : List<Employee> by employees, Logger by logger {

    val averageAge by lazy {
        info("init averageAge")
        employees.map { it.age }.average()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Company::class.java)
    }

}

class Employee(name_: String, age_: Int) {
    var age: Int by FieldAccessTracer(age_)
    var name:String by FieldAccessTracer(name_)
}

class FieldAccessTracer<T>(private var value: T): ReadWriteProperty<Any?, T> {

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        traceGetter(thisRef, property.name)
        return value
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        traceSetter(thisRef, property.name)
        this.value = value
    }

    companion object {

        val registry = ConcurrentHashMap<String, Int>()
        fun key(thisRef: Any?, propName: String, operation: String) = "${thisRef?.javaClass?.name ?: "<unknown>"}.$propName.$operation"

        private fun registerPropertyAccess(thisRef: Any?, propName: String, operation: String) {
            val key = key(thisRef, propName, operation)
            registry.put(key, registry.getOrDefault(key, 0) + 1)
        }

        fun traceGetter(thisRef: Any?, name: String): Unit = registerPropertyAccess(thisRef, name, "get")

        fun traceSetter(thisRef: Any?, name: String): Unit = registerPropertyAccess(thisRef, name, "set")
    }
}

