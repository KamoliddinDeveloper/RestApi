package com.example.restapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.databinding.ItemRvBinding
import com.example.restapi.models.MyTodo

class TodoAllAdapter(var list:List<MyTodo> = emptyList()):RecyclerView.Adapter<TodoAllAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){

       fun onBind(myTodo: MyTodo, position:Int){
           itemRvBinding.itemTvName.text = myTodo.sarlavha
           itemRvBinding.itemTvAbout.text = myTodo.matn
           itemRvBinding.itemTvHolati.text = myTodo.holati
           itemRvBinding.itemTvMuddati.text = myTodo.oxirgi_muddat
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}