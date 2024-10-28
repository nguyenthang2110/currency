package com.example.currency

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomSpinner( val activity: Activity, val list2: List<Outdata>) :ArrayAdapter<Outdata>(activity,R.layout.list_country) {
    private class ViewHolder(view: View) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val des: TextView = view.findViewById(R.id.textView)
    }
    override fun getCount(): Int {
        return list2.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initview(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initview(position,convertView,parent)
    }
    //ham xu ly view
    private fun initview(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowview = context.inflate(R.layout.list_country,parent,false)

        val image=rowview.findViewById<ImageView>(R.id.imageView)
        val des=rowview.findViewById<TextView>(R.id.textView)

        image.setImageResource(list2[position].image)
        des.text=list2[position].des
        return rowview
    }
}
