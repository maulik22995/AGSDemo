package com.ags.maulikpract.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ags.maulikpract.R
import com.ags.maulikpract.databinding.ItemDepartmentBinding
import com.ags.maulikpract.model.Department

class DropDownDepartment(val context: Context) :
    BaseAdapter() {
    private var items = ArrayList<Department>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_department, parent, false
            ) as ItemDepartmentBinding
        binding.dataModel = items[position]
        return binding.root
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size
    }

    fun setSelectedItem(selectedItem: Int) {
        items.map { it.isSelected = false }
        items[selectedItem].isSelected = true
    }

    fun setItems(items: ArrayList<Department>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<Department> {
        return items
    }

}