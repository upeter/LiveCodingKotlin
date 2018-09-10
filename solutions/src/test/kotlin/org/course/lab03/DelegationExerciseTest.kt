package org.course.lab03

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import io.kotlintest.Description
import io.kotlintest.TestResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.WordSpec
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaField
import ch.qos.logback.classic.Logger as LogbackLogger

class DelegationExerciseTest : WordSpec() {
    val listAppender = ListAppender<ILoggingEvent>()

    override fun listeners(): List<TestListener> = listOf(TimerListener(listAppender))


    init {

        "DelegationExercise.ClassDelegation" should {
            "Company class should delegate List and Logger class" {
                val c = Company(emptyList())
                c::class.functions.any { it.name == "isEmpty" } shouldBe true
                c::class.functions.any { it.name == "info" }
            }
        }
        "DelegationExercise.PropertyDelegation" should {
            "Company should initialize averageAge lazily and log first access with info"  {
                val c = Company(listOf(Employee("Jack", 42)))
                val lazyField = c::class.declaredMemberProperties.find { it.javaField?.type == Lazy::class.java }
                lazyField shouldNotBe null
                lazyField?.name shouldBe "averageAge"
                listAppender.list.clear()
                c.averageAge shouldBe 42.0
                //second time the field must be initialized, so no info statement is logged
                c.averageAge shouldBe 42.0
                listAppender.list.size shouldBe 1
            }
            "Access to Employee field age (getter and setter) should be registered by FieldAccessTracer"  {
                FieldAccessTracer.registry.clear()
                val joe = Employee("Joe", 42)
                //tracing age
                joe.age shouldBe 42
                joe.age = 24
                joe.age shouldBe 24
                FieldAccessTracer.registry.keys().toList().size shouldBe 2
                val ageGetterKey = FieldAccessTracer.key(joe, "age", "get")
                FieldAccessTracer.registry.get(ageGetterKey) shouldBe 2
                val ageSetterKey = FieldAccessTracer.key(joe, "age", "set")
                FieldAccessTracer.registry.get(ageSetterKey) shouldBe 1
            }
                "Access to Employee field name (getter and setter) should be registered by FieldAccessTracer"  {
                    FieldAccessTracer.registry.clear()
                    val joe = Employee("Joe", 42)
                    //tracing name
                    joe.name shouldBe "Joe"
                    joe.name = "John"
                    joe.name shouldBe "John"
                    FieldAccessTracer.registry.keys().toList().size shouldBe 2
                    val nameGetterKey = FieldAccessTracer.key(joe, "name", "get")
                    FieldAccessTracer.registry.get(nameGetterKey) shouldBe 2
                    val nameSetterKey = FieldAccessTracer.key(joe, "name", "set")
                    FieldAccessTracer.registry.get(nameSetterKey) shouldBe 1
                }


        }
    }

    class TimerListener(val listAppender: ListAppender<ILoggingEvent>) : TestListener {
        private val logger: LogbackLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as LogbackLogger

        override fun beforeTest(description: Description): Unit {
            logger.addAppender(listAppender)
            listAppender.start()
        }

        override fun afterTest(description: Description, result: TestResult): Unit {
            listAppender.stop()
            listAppender.list.clear()
            logger.detachAppender(listAppender)
        }
    }
}
