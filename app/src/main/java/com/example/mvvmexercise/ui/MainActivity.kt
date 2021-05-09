package com.example.mvvmexercise.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexercise.Expense
import com.example.mvvmexercise.R
import com.example.mvvmexercise.ui.veiwModels.FactoryShowData
import com.example.mvvmexercise.ui.veiwModels.ShowDataViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ShowDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, FactoryShowData(this))[ShowDataViewModel::class.java]

        addItemButton.setOnClickListener {
            setupDialogBox()
        }


    }


    private fun setupDialogBox() {

        val alertView = LayoutInflater.from(this).inflate(R.layout.alert_dialoug_box, null)

        val itemName = alertView.findViewById<EditText>(R.id.itemNameEditText)
        val itemPrice = alertView.findViewById<EditText>(R.id.itemPriceEditText)

        itemName.error = "It should not be empty"
        itemPrice.error = "Price should not be empty"

        AlertDialog.Builder(this)
            .setView(alertView)
            .setPositiveButton("Add") { dialog, id ->
                if (itemName.text.toString().isBlank()
                    || itemPrice.text.toString().isBlank()
                ) {
                    dialog.cancel()
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                } else {

                    viewModel.addItem(
                        Expense(
                            viewModel.totalExpenses.value!!.size,
                            itemName.text.toString(),
                            itemPrice.text.toString().toDouble()
                        )
                    )

                }
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }
            .setCancelable(false)
            .show()
    }
}