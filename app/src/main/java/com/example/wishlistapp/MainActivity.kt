package com.example.wishlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.ProductAdapter

class MainActivity : AppCompatActivity() {
    private var products: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var productsRv = findViewById<RecyclerView>(R.id.productsRv)

        val nameView = findViewById<EditText>(R.id.productEv)
        val urlView = findViewById<EditText>(R.id.urlEv)
        val priceView = findViewById<EditText>(R.id.priceEv)
        val submitButton = findViewById<Button>(R.id.submit_button)



        val adapter = ProductAdapter(products)
        productsRv.adapter = adapter
        productsRv.layoutManager = LinearLayoutManager(this)


        submitButton.setOnClickListener {
            
            val name = nameView.text.toString()
            val url = urlView.text.toString()
            val price = priceView.text.toString()

            val priceFormat=price.toDoubleOrNull()
            if(priceFormat !=null && name.isNotBlank() && url.isNotBlank()){
                val product = Product(name, price, url)
                products.add(product)
                adapter.notifyItemInserted(products.size-1)
                productsRv.scrollToPosition(products.size-1)
                Log.d("Added a product", "Total products are ${products.size}")

                nameView.text.clear()
                urlView.text.clear()
                priceView.text.clear()
            }else{
                Toast.makeText(this,"Please enter a valid name,url and price",Toast.LENGTH_SHORT).show()
            }
        }

    }

}