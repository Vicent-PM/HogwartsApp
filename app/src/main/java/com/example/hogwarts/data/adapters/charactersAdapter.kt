package com.example.hogwarts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.databinding.HolderCharactersBinding

class charactersAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<charactersAdapter.Holder>(), Filterable {

    private val characterList = ArrayList<Characters>()
    private var characterListCopy = ArrayList<Characters>()

    interface OnItemClickListener {
        fun onItemClick(characters: Characters)
    }

    inner class Holder(val binding: HolderCharactersBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderCharactersBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return characterListCopy.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val character: Characters = characterListCopy[position]
        if(character.image != ""){
            Glide.with(holder.itemView).load(character.image)
                .override(Target.SIZE_ORIGINAL)
                .into(holder.binding.imageView)
            holder.binding.tvCharaName.text = character.name
        }



        holder.itemView.setOnClickListener {
            listener.onItemClick(character)
        }
    }

    fun update(list: List<Characters>?){
        characterList.clear()
        if (list != null) {
            characterList.addAll(list)
        }
        characterListCopy.clear()
        if (list != null) {
            characterListCopy.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charFilter = p0.toString()
                if(charFilter.isEmpty()){
                    characterListCopy = characterList
                } else {
                    characterListCopy = characterList.filter {
                        it.name?.lowercase()!!.contains(charFilter.lowercase())
                    } as ArrayList<Characters>
                }

                val filterResults = FilterResults()
                filterResults.values = characterListCopy
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                characterListCopy = p1?.values as ArrayList<Characters>
                notifyDataSetChanged()
            }

        }
    }


}