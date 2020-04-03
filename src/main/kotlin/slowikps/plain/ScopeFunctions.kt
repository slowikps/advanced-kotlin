package slowikps.plain

class Person {
    var name: String? = null
    var surname: String? = null

    var age: Int? = null
}
//https://kotlinlang.org/docs/reference/scope-functions.html
fun main() {
    val number = 11
    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }

    val personRun: Unit = Person().run {
        age = 35
        name = "Pawel"
        surname = "Slowinski"
    }

    val personWith: Unit = with(Person()) {
        age = 35
        name = "Pawel"
        surname = "Slowinski"
    }

    val personApply: Person = Person().apply {
        age = 35
        name = "Pawel"
        surname = "Slowinski"
    }


    val personLet: Unit = Person().let {
        it.age = 35
        it.name = "Pawel"
        it.surname = "Slowinski"
    }

    val personAlso: Person = Person().also {
        it.age = 35
        it.name = "Pawel"
        it.surname = "Slowinski"
    }
}