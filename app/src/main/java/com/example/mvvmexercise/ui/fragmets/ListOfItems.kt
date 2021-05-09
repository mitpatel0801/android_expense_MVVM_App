package com.example.mvvmexercise.ui.fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexercise.R
import com.example.mvvmexercise.ui.adapter.ExpenseAdapter
import com.example.mvvmexercise.ui.veiwModels.FactoryShowData
import com.example.mvvmexercise.ui.veiwModels.ShowDataViewModel
import kotlinx.android.synthetic.main.fragment_list_of_items.view.*

class ListOfItems : Fragment() {


    private lateinit var viewModel: ShowDataViewModel
    private lateinit var myRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_of_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            FactoryShowData(requireActivity())
        )[ShowDataViewModel::class.java]

        myRecyclerView = view.recycleView

        viewModel.totalExpenses.observe(viewLifecycleOwner, {
            myRecyclerView.adapter = ExpenseAdapter(it) { expense ->
                expense.isClicked = !expense.isClicked

//                if (expense.isClicked) {
//                    view.setBackgroundColor(Color.CYAN)
//                }
                findNavController().navigate(
                    ListOfItemsDirections.actionListOfItemsToEditItemFragment(
                        expense.id
                    )
                )
            }

        })

        myRecyclerView.layoutManager = LinearLayoutManager(context)

    }
}