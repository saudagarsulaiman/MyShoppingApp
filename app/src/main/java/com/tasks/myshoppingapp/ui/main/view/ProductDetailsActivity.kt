package com.tasks.myshoppingapp.ui.main.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tasks.myshoppingapp.R
import com.tasks.myshoppingapp.databinding.ActivityProductDetailsBinding
import com.tasks.myshoppingapp.ui.main.view.DashboardActivity.Companion.productDetails
import com.tasks.myshoppingapp.ui.main.view.DashboardActivity.Companion.showStrikeThrough

class ProductDetailsActivity : AppCompatActivity() {

    private var context: Context = this
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {  onBackPressedDispatcher.onBackPressed() }
//        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                    finish()
//            }
//        })

        binding.tvItemPriceOriginal.showStrikeThrough(true)
        binding.tvItemTitle.text = productDetails.name
        binding.tvItemBrand.text = "Brand : " + productDetails.brand
        binding.tvItemDes.text = "Description : " + productDetails.productDesc
        binding.tvItemPriceOriginal.text = "Rs." + productDetails.price
        binding.tvItemPrice.text = "Rs." + productDetails.offerPrice
        Glide.with(context).load(productDetails.productUrl).error(
            context.resources.getDrawable(
                R.drawable.item
            )
        ).into(binding.ivItemImage)
    }
}