package com.example.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
   // launch
//       val jobCity =  lifecycleScope.launch {
//           binding.progress.isVisible = true
//
//                city()
//            }
//       val jobTemp= lifecycleScope.launch {
//           binding.progress.isVisible = true
//                temp()
//            }
//            lifecycleScope.launch {
//                binding.button.isEnabled=false
//                jobCity.join()
//                jobTemp.join()
//                binding.progress.isVisible = false
//                binding.button.isEnabled = true
//            }


            //async
            val deferredCity=lifecycleScope.async {
                binding.progress.isVisible = true
                     city()
                loadCity()
            }
            val deferredTemp=lifecycleScope.async{
                binding.progress.isVisible = true
                temp()
                loadTemp()
            }

            lifecycleScope.launch {
                binding.button.isEnabled=false
             val city=   deferredCity.await()
              val temp=  deferredTemp.await()
                binding.progress.isVisible = false
                binding.button.isEnabled = true
                Toast.makeText(this@MainActivity,"$city $temp",Toast.LENGTH_SHORT).show()
            }

        }


    }
    suspend fun temp(){
        delay(5000)
        binding.textViewTemp.text = loadTemp()
    }
    suspend fun city(){
        delay(5000)
        binding.textViewCity.text = loadCity()
    }

   private  fun loadTemp():String {
       return "temperature : 22"
    }


    private  fun loadCity() :String{
        binding.progress.isVisible = true
        return "city : lviv"

    }

}