package org.course.lab03

import kotlin.reflect.full.declaredMemberProperties

/**
 * Implement the matchOnInputType(param:Any?):String method using a when expression with
 * all the branch conditions required to make the unittest succeeds.
 */
object ControlFlowExercise1 {

    fun matchOnInputType(param: Any?): String =  TODO("implement")

}

/**
 * The following classes and methods are part of ControlFlowExercise2 and ControlFlowExercise3
 */
class PropChange<T>(val prop: String, val old: T?, val new: T?)

inline fun <reified T : Any> T?.properties(): List<Pair<String, Any?>> =
        this?.let { T::class.declaredMemberProperties.map { it.name to it.call(this) } } ?: emptyList()

inline fun <reified T : Any> calcPropChanges(before: T?, after: T?): List<PropChange<Any?>> {
    val oldAndNew = before.properties() + after.properties()
    val propChanges = oldAndNew.groupBy { (propName, _) -> propName }
            .map { (propName, oldAndNewProp) ->
                PropChange<Any?>(
                        prop = propName,
                        old = oldAndNewProp[0].second,
                        new = oldAndNewProp[1].second)
            }
    return propChanges
}

/**
 * Take a look at the @see PropChange class. It represents a value object that keeps
 * the value before and after a change of a particular property.
 * Implement the  show(changes:List<PropChange<Any?>>, printFun:(String) -> Unit) method by
 * making use of a for loop with destructuring in order to print all the fields of @see PropChange
 * objects in the following format: "<prop>: was=<old> is=<new>:". What do you need to do
 * to make destructuring work?
 * For printing use the provided lambda printFun and make the unittest succeed.
 */
object ControlFlowExercise2 {

    fun show(propChanges: List<PropChange<Any?>>, printFun:(String) -> Unit = System.out::println) {
        TODO("implement")
    }
}

/**
 * Take a look at the @see ControlFlowExerciseTest3. In the test setup of two @see UserPreference instances
 * are used (existing and new) for which we want to know which attributes were changed in the new version compared to the existing one.
 * Implement the diffResult(prop: PropChange<T>):String method using a when expression that figures out
 * which property value has been added, removed, changed or left unchanged.
 * How can destructuring help to make your code more readable?
 * Make the unittest succeed.
 */
object ControlFlowExercise3 {

    fun <T> diffResult(prop: PropChange<T>): String {
        TODO("implement")
    }

}








