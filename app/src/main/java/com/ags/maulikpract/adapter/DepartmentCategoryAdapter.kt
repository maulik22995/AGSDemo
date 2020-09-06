package com.ags.maulikpract.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ags.maulikpract.BR
import com.ags.maulikpract.R
import com.ags.maulikpract.databinding.ItemCategoryDepartmentBinding
import com.ags.maulikpract.databinding.ItemDepartmentBinding
import com.ags.maulikpract.databinding.ItemEmployeeBinding
import com.ags.maulikpract.model.DepartCategory
import com.ags.maulikpract.model.Employee
import com.ags.maulikpract.utils.OnItemClickListener

class DepartmentCategoryAdapter(
    private var departData: ArrayList<DepartCategory>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DepartmentCategoryAdapter.DepartmentCategoryHolder>(),
    OnItemClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentCategoryHolder {
        return DepartmentCategoryHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category_department,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return departData.size
    }

    override fun onBindViewHolder(holder: DepartmentCategoryHolder, position: Int) {
        holder.bind(departData[position], position)
        (holder.viewBinding as ItemCategoryDepartmentBinding).ivAdd.setOnClickListener {
            listener.onItemClick(it, departData[position], position)
        }
    }


    fun setData(data: ArrayList<DepartCategory>) {
        departData = data
        notifyDataSetChanged()
    }

    fun addData(data: DepartCategory) {
        departData.add(data)
        notifyItemChanged(departData.size)
    }

    private fun setDataForListItem(
        viewBinding: ViewDataBinding,
        data: DepartCategory,
        position: Int
    ) {
        val itemDepartBinding = viewBinding as? ItemCategoryDepartmentBinding
        itemDepartBinding?.let { bindingData ->
            val adapter = EmployeeAdapter(data.employee, this)
            adapter.setData(data.employee)
            bindingData.reyEmployee.adapter = adapter
        }

    }

    inner class DepartmentCategoryHolder(val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: DepartCategory, position: Int) {
            viewBinding.setVariable(BR.dataModel, data)
            viewBinding.executePendingBindings()
            setDataForListItem(viewBinding, data, position)
        }

    }

    override fun onItemClick(view: View, data: Any, position: Int) {
        when(view.id){
            R.id.tvRemove ->{
                val employee = data as Employee
                departData.filter { it.id == employee.deptId }.map {
                    it.employee.removeAt(position)
                }
                departData.filter { it.id == employee.deptId }.forEachIndexed { index, departCategory ->
                    notifyItemChanged(index)
                }
            }
        }
    }
}