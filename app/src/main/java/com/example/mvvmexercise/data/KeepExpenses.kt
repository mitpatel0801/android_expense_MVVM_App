package com.example.mvvmexercise

class KeepExpenses {
    companion object {
        val listOfExpense: MutableList<Expense> = mutableListOf(
            Expense(0, "Food", 100.0),
            Expense(1, "Bill", 200.0),
            Expense(2, "Grocery", 150.0),
            Expense(3, "Water bill", 300.0),
        )

        fun addExpense(name: String, cost: Double) {
            listOfExpense.add(Expense(id = listOfExpense.size, name = name, cost = cost))
        }
    }
}

data class Expense(
    val id: Int,
    var name: String,
    var cost: Double,
    var isClicked:Boolean = false
)

