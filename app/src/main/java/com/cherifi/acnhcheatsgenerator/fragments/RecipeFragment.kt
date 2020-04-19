package com.cherifi.acnhcheatsgenerator.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cherifi.acnhcheatsgenerator.adapters.MyRecipeRecyclerViewAdapter
import com.cherifi.acnhcheatsgenerator.R
import com.cherifi.acnhcheatsgenerator.data.RemoteDataGetter

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RecipeFragment.OnListFragmentInteractionListener] interface.
 */
class RecipeFragment : Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var recipes: MutableList<Pair<String, String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipes = RemoteDataGetter.recipes
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    MyRecipeRecyclerViewAdapter(
                        recipes,
                        listener
                    )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Pair<String, String>)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecipeFragment()
    }
}
