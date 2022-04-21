package com.nitish.viewpagerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.nitish.viewpagerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var layoutViewPagerAdapter: ViewsSliderAdapter
    lateinit var binding: ActivityMainBinding
    var layoutArray = arrayOf(R.layout.layout1,R.layout.layout2, R.layout.layout3)
    lateinit var dotsRecycler : DotsRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        dotsRecycler = DotsRecycler(this,layoutArray.size, 0)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = dotsRecycler

        layoutViewPagerAdapter = ViewsSliderAdapter(layoutArray)
        binding.viewPager.setAdapter(layoutViewPagerAdapter)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(position == 0){
                    binding.btnPrev.visibility = View.GONE
                }else{
                    binding.btnPrev.visibility = View.VISIBLE
                }
                if(position == (layoutArray.size-1)){
                    binding.btnNext.visibility = View.GONE
                }else{
                    binding.btnNext.visibility = View.VISIBLE
                }

                dotsRecycler.updatePosition(position)
            }
        })

        binding.btnPrev.setOnClickListener {
            if(binding.viewPager.currentItem < layoutArray.size && binding.viewPager.currentItem != 0){
                binding.viewPager.currentItem = binding.viewPager.currentItem -1
            }
        }

        binding.btnNext.setOnClickListener {
            if(binding.viewPager.currentItem < layoutArray.size){
                binding.viewPager.currentItem = binding.viewPager.currentItem +1
            }
        }
    }
}
class ViewsSliderAdapter(var layouts: Array<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    override fun getItemViewType(position: Int): Int {
        return layouts[position]
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    inner class SliderViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

    }
}