package com.ciandt.estagio2020.setimaaula

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:imageGlide")
fun setImageWithGlide(view: ImageView, imageURL: String) {
    Glide.with(view.context)
            .load(imageURL)
            .into(view)
}

@BindingAdapter("android:teste")
fun bindingTeste(view: View, teste: String) {
    Log.d("TAG",teste)
}
