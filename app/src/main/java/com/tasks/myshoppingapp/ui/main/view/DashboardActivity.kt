package com.tasks.myshoppingapp.ui.main.view

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasks.myshoppingapp.R
import com.tasks.myshoppingapp.data.api.ApiHelper
import com.tasks.myshoppingapp.data.api.RetrofitBuilder
import com.tasks.myshoppingapp.data.model.ProductDetails
import com.tasks.myshoppingapp.data.model.ProductsModel
import com.tasks.myshoppingapp.data.model.ProfileModel
import com.tasks.myshoppingapp.databinding.ActivityDashboardBinding
import com.tasks.myshoppingapp.ui.base.ViewModelFactory
import com.tasks.myshoppingapp.ui.main.adapter.MyProductsRAdapter
import com.tasks.myshoppingapp.ui.main.viewmodel.MainViewModel
import com.tasks.myshoppingapp.utils.Status

class DashboardActivity : AppCompatActivity() {

    private var context: Context = this
    private lateinit var binding: ActivityDashboardBinding

    private lateinit var viewModel: MainViewModel

    companion object {
        lateinit var productDetails: ProductDetails

        fun TextView.showStrikeThrough(show: Boolean) {
            paintFlags = if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupProductsObservers()

        binding.rviewProducts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        setProducts()
        binding.txtProducts.setOnClickListener { setProducts() }
        binding.txtProfile.setOnClickListener { setProfile() }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setProducts() {
        binding.txtProducts.setTextColor(resources.getColor(R.color.white))
        binding.txtProducts.background = resources.getDrawable(R.drawable.rec_pink_c25)
        binding.txtProfile.setTextColor(resources.getColor(R.color.black))
        binding.txtProfile.background = resources.getDrawable(R.color.white)
        setupProductsObservers()
    }

    private fun setProfile() {
        binding.txtProfile.setTextColor(resources.getColor(R.color.white))
        binding.txtProfile.background = resources.getDrawable(R.drawable.rec_pink_c25)
        binding.txtProducts.setTextColor(resources.getColor(R.color.black))
        binding.txtProducts.background = resources.getDrawable(R.color.white)
        setupProfileObservers()
    }


    private fun setupProductsObservers() {
        viewModel.getProducts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rviewProducts.visibility = View.VISIBLE
                        binding.ivOops.visibility = View.GONE
                        binding.includeProfile.lytParent.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { product -> setProductsData(product) }
                    }
                    Status.ERROR -> {
                        binding.includeProfile.lytParent.visibility = View.GONE
                        binding.ivOops.visibility = View.VISIBLE
                        binding.rviewProducts.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.includeProfile.lytParent.visibility = View.GONE
                        binding.ivOops.visibility = View.GONE
                        binding.rviewProducts.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setProductsData(product: ProductsModel) {
        println("Setting Products Data...")
        try {
        binding.rviewProducts.adapter = MyProductsRAdapter(context, product.data.products)
        } catch (e: Exception) {
            binding.ivOops.visibility = View.VISIBLE
            e.printStackTrace()
        }
    }

    private fun setupProfileObservers() {
        viewModel.getProfile().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.includeProfile.lytParent.visibility = View.VISIBLE
                        binding.rviewProducts.visibility = View.GONE
                        binding.ivOops.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { user -> setProfileData(user) }
                    }
                    Status.ERROR -> {
                        binding.includeProfile.lytParent.visibility = View.GONE
                        binding.rviewProducts.visibility = View.GONE
                        binding.ivOops.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.ivOops.visibility = View.GONE
                        binding.includeProfile.lytParent.visibility = View.GONE
                        binding.rviewProducts.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setProfileData(user: ProfileModel) {
        println("Setting User Data...")
        try {
            binding.includeProfile.txtUsername.text = user.username
            binding.includeProfile.txtPoints.text = user.pointsEarned
            binding.includeProfile.txtWallet.text = user.walletBalance
            binding.includeProfile.txtInitials.text =
                user.firstname.substring(0, 1) + user.lastName.substring(0, 1)
            binding.includeProfile.txtFirstnameLastname.text = user.firstname + " " + user.lastName
            binding.includeProfile.txtDob.text = user.dob
            binding.includeProfile.txtAddress.text = "Address : " + user.address
        } catch (e: Exception) {
            binding.ivOops.visibility = View.VISIBLE
            e.printStackTrace()
        }
    }

}