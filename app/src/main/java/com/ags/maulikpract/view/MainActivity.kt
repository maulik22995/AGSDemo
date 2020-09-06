package com.ags.maulikpract.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.observe
import com.ags.maulikpract.R
import com.ags.maulikpract.adapter.DepartmentCategoryAdapter
import com.ags.maulikpract.adapter.DropDownDepartment
import com.ags.maulikpract.base.BaseActivity
import com.ags.maulikpract.databinding.ActivityMainBinding
import com.ags.maulikpract.model.DepartCategory
import com.ags.maulikpract.model.Department
import com.ags.maulikpract.model.Employee
import com.ags.maulikpract.utils.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainActivityViewModel, MainActivityState>(R.layout.activity_main),
    OnItemClickListener {

    lateinit var spinnerAdapter: DropDownDepartment
    lateinit var adapterMain: DepartmentCategoryAdapter
    override val viewModel: MainActivityViewModel by viewModel()
    var arrayDepartment = arrayListOf<Department>()
    private val departCatList: ArrayList<DepartCategory> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        viewModel.getAllDepartment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when (id) {
            R.id.save -> {

            }
        }
        return true
    }

    private fun initViews() {
        spinnerAdapter =
            DropDownDepartment(this)
        binding.spDepartment.adapter = spinnerAdapter
        binding.spDepartment.onItemSelectedListener =
            listener


        adapterMain = DepartmentCategoryAdapter(departCatList, this)
        reyData.apply {
            adapter = adapterMain
        }
    }


    override fun observeViewState(state: MainActivityState) {
        state.departmentList.observe(this) {
            spinnerAdapter?.let {
                setDepartList()
                spinnerAdapter.setItems(arrayDepartment)
            }
        }
    }

    private fun setDepartList() {
        state.departmentList.value?.forEachIndexed { _, departData ->
            arrayDepartment.add(Department(departData.id, departData.departName))
        }
    }

    private val listener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            state.isDropDownOpen.value = false
        }

        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View?,
            position: Int,
            id: Long
        ) {
            spinnerAdapter.setSelectedItem(position)
            state.isDropDownOpen.value = false
            if (position != 0) {
                addToList(position)
            }
        }
    }

    private fun addToList(position: Int) {
        val selectedFromSpinner = arrayDepartment[position]
        if (departCatList.isEmpty() || departCatList.filter { it.id == selectedFromSpinner.id }.size == 0) {
            /*departCatList.add(
                DepartCategory(
                    id = selectedFromSpinner.id,
                    departName = selectedFromSpinner.departName
                )
            )*/
            adapterMain?.let {
                adapterMain.addData(
                    DepartCategory(
                        id = selectedFromSpinner.id,
                        departName = selectedFromSpinner.departName
                    )
                )
            }
        }
    }

    override fun onItemClick(view: View, data: Any, position: Int) {
        when (view.id) {
            R.id.ivAdd -> {
                val departMent = data as DepartCategory
                departCatList.filter { it.id == departMent.id }.map {
                    it.employee.add(Employee(deptId = it.id,email = ""))
                }
                adapterMain.notifyItemChanged(position)
            }
        }
    }
}