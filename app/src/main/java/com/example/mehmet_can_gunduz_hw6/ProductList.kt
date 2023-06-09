package com.example.mehmet_can_gunduz_hw6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.mehmet_can_gunduz_hw6.adapter.ProductCustomAdapter
import com.example.mehmet_can_gunduz_hw6.configs.ApiClient
import com.example.mehmet_can_gunduz_hw6.models.DummyProducts
import com.example.mehmet_can_gunduz_hw6.models.Product
import com.example.mehmet_can_gunduz_hw6.services.DummyServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductList : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var btn_search : Button
    lateinit var edt_search : EditText
    lateinit var  customAdapter: ArrayAdapter<Product>
    var productListTemp= mutableListOf<Product>()
    lateinit var dummyService: DummyServices
    var productList= mutableListOf<Product>()
    var newProductList = mutableListOf<Product>()

    companion object{
    lateinit var productDetail:Product
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        listView = findViewById(R.id.ListView)
        btn_search = findViewById(R.id.btn_search)
        edt_search = findViewById(R.id.edt_search)


        dummyService= ApiClient.getClient().create(DummyServices::class.java)
        dummyService.products().enqueue(object : Callback<DummyProducts>{
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                for(product in response.body()!!.products){
                    productList.add(product)
                }
                customAdapter = ProductCustomAdapter(this@ProductList,productList)
                listView.adapter=customAdapter
            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                Toast.makeText(this@ProductList,"Internet or Server Fail", Toast.LENGTH_LONG).show()

            }

        })

        btn_search.setOnClickListener(OnClickListener)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            productDetail=productList.get(i)
            val intent = Intent(this, ProductDetail::class.java)
            startActivity(intent)
        }

    }
    val OnClickListener= View.OnClickListener {
        val edtSearch=edt_search.text.toString()
        dummyService.searchProducts(edtSearch).enqueue(object :Callback<DummyProducts>{
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                Log.e("searchProduct",response.body().toString())
                productListTemp=productList
                productList.clear()
                for(product in response.body()!!.products){
                    productList.add(product)
                }
                customAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                Log.e("eroor","server error")
            }

        })

    }
}