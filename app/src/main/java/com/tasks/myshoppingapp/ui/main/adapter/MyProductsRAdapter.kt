package com.tasks.myshoppingapp.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasks.myshoppingapp.R
import com.tasks.myshoppingapp.data.model.ProductDetails
import com.tasks.myshoppingapp.databinding.AdapterProductItemsBinding
import com.tasks.myshoppingapp.ui.main.view.DashboardActivity.Companion.productDetails
import com.tasks.myshoppingapp.ui.main.view.DashboardActivity.Companion.showStrikeThrough
import com.tasks.myshoppingapp.ui.main.view.ProductDetailsActivity


class MyProductsRAdapter(
    private val context: Context, private val mList: ArrayList<ProductDetails>
) : RecyclerView.Adapter<MyProductsRAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterProductItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterProductItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.tvItemPriceOriginal.showStrikeThrough(true)
            binding.tvItemTitle.text = mList[position].name
            binding.tvItemDes.text = mList[position].productDesc
            binding.tvItemPriceOriginal.text = "Rs." + mList[position].price
            binding.tvItemPrice.text = "Rs." + mList[position].offerPrice
            Glide.with(context).load(mList[position].productUrl).error(
                context.resources.getDrawable(
                    R.drawable.item
                )
            ).circleCrop().into(binding.ivItemImage)

            if (position == 1) {
                binding.txtRewards.visibility = VISIBLE
                binding.clytItem.visibility = GONE
            } else {
                binding.txtRewards.visibility = GONE
                binding.clytItem.visibility = VISIBLE
            }

            itemView.setOnClickListener {
                if (position == 1)
                    Toast.makeText(context,"Thank You for joining our reward program",Toast.LENGTH_SHORT).show()
                else{
                    productDetails = mList[position]
                    context.startActivity(Intent(context, ProductDetailsActivity::class.java))
                }

            }

        }
    }

}