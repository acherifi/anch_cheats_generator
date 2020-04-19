package com.cherifi.acnhcheatsgenerator

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cherifi.acnhcheatsgenerator.data.Cheat
import com.cherifi.acnhcheatsgenerator.data.CheatList
import kotlinx.android.synthetic.main.fragment_file_view_dialog_list_dialog.*
import kotlinx.android.synthetic.main.fragment_file_view_dialog_list_dialog_item.view.*

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    FileViewDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class FileViewDialogFragment : BottomSheetDialogFragment() {

    lateinit var cheatList: MutableList<Cheat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cheatList = CheatList.list
        return inflater.inflate(R.layout.fragment_file_view_dialog_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = FileViewAdapter(cheatList.size)
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_file_view_dialog_list_dialog_item,
            parent,
            false
        )
    ) {

        internal val text: TextView = itemView.text
    }

    private inner class FileViewAdapter internal constructor(private val mItemCount: Int) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = cheatList[position].toString()
        }

        override fun getItemCount(): Int {
            return mItemCount
        }
    }
}
