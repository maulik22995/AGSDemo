package com.ags.maulikpract.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ags.maulikpract.BR
import com.ags.maulikpract.R
import com.ags.maulikpract.databinding.ItemCategoryDepartmentBinding
import com.ags.maulikpract.databinding.ItemEmployeeBinding
import com.ags.maulikpract.model.DepartCategory
import com.ags.maulikpract.model.Employee
import com.ags.maulikpract.utils.OnItemClickListener

class EmployeeAdapter(
    private var empData: ArrayList<Employee>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_employee,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return empData.size
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bind(empData[position])
        (holder.viewBinding as ItemEmployeeBinding).tvRemove.setOnClickListener {
            listener.onItemClick(it,empData[position],position)
        }
    }


    fun setData(data: ArrayList<Employee>) {
        empData = data
        notifyDataSetChanged()
    }

    fun addData(data: Employee) {
        empData.add(data)
        notifyItemChanged(empData.size)
    }


    class EmployeeHolder(val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: Employee) {
            viewBinding.setVariable(BR.dataModel, data)
            viewBinding.executePendingBindings()
        }
    }
}