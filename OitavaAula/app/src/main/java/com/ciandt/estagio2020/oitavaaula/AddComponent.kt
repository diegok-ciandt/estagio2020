package com.ciandt.estagio2020.oitavaaula

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.ciandt.estagio2020.oitavaaula.database.Person
import com.ciandt.estagio2020.oitavaaula.databinding.AddItemLayoutBinding

class AddComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: AddItemLayoutBinding? = null

    private var viewModel: MainViewModel? = null

    fun setLifecycleOwner (lifecycleOwner: LifecycleOwner) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.add_item_layout,
            this,
            true)

        binding?.lifecycleOwner = lifecycleOwner
        setupOnClickSave()
    }

    fun setViewModel(viewModel : MainViewModel) {
        this.viewModel = viewModel
    }

    fun setPerson(person: Person?) {
        person?.let {
            binding?.editTextAge?.setText(it.age.toString())
            binding?.editTextFirstName?.setText(it.firstName)
            binding?.editTextLastName?.setText(it.lastName)
        }
    }

    private fun setupOnClickSave() {
        binding?.buttonSave?.setOnClickListener(OnClickListener {
            this@AddComponent.visibility = View.GONE
            viewModel?.insertPerson(Person(
                binding?.editTextFirstName?.text.toString(),
                binding?.editTextLastName?.text.toString(),
                binding?.editTextAge?.text.toString().toInt()))
        })
    }

}