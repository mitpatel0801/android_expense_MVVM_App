package com.example.mvvmexercise.ui.fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmexercise.Expense
import com.example.mvvmexercise.R
import com.example.mvvmexercise.ui.veiwModels.FactoryShowData
import com.example.mvvmexercise.ui.veiwModels.ShowDataViewModel
import kotlinx.android.synthetic.main.alert_dialoug_box.view.*
import kotlinx.android.synthetic.main.fragment_edit_item.view.*
import kotlin.math.exp

/**
 * A simple [Fragment] subclass.
 * Use the [EditItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditItemFragment : Fragment() {

    private lateinit var model: ShowDataViewModel
    private lateinit var expense: Expense
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(
            requireActivity(),
            FactoryShowData(requireActivity())
        )[ShowDataViewModel::class.java]

        val id: Int? = arguments?.let { EditItemFragmentArgs.fromBundle(it).expenseId }
        expense = model.totalExpenses.value!!.find { it.id == id }!!

        with(view)
        {
            itemNameText.setText(expense!!.name)
            itemPriceText.setText("${expense!!.cost}")

            itemNameText.error = "It should not be empty"
            itemPriceText.error = "Price should not be empty"
        }


        view.update.setOnClickListener {
            with(view)
            {
                val name = itemNameText.text.toString()
                val price = itemPriceText.text.toString()
                if (name.isNullOrBlank() || price.isNullOrBlank()) {
                    Toast.makeText(this@EditItemFragment.context, "Invalid inputs", Toast.LENGTH_SHORT).show()
                }else{
                    updateExpenses(name,price)
                }
            }
            findNavController().navigate(EditItemFragmentDirections.actionEditItemFragmentToListOfItems())
        }
    }

    private fun updateExpenses(name:String,price:String) {
        expense.name = name
        expense.cost = price.toDouble()
        model.updateData()
    }


}