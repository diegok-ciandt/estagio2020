package com.ciandt.estagio2020.setimaaula

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide

class MyComponent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
defStyle: Int = 0)
: ConstraintLayout(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.my_componet, this)
    }

    fun setName(strName : String) {
        findViewById<TextView>(R.id.textViewComponent).setText(strName)
        Toast.makeText(context, strName, Toast.LENGTH_SHORT).show()
    }

    fun setUrl(strUrl : String) {
        val img = findViewById<ImageView>(R.id.image)

        Glide.with(context)
                .load(strUrl)
                .into(img)
    }
}