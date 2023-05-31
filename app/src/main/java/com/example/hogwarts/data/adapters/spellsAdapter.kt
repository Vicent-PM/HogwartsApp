package com.example.hogwarts.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.data.models.getSpells.Spells
import com.example.hogwarts.databinding.HolderSpellsBinding

class spellsAdapter: RecyclerView.Adapter<spellsAdapter.Holder>(), Filterable {

    private val spellsList = ArrayList<Spells>()
    private var spellsListCopy = ArrayList<Spells>()

    inner class Holder(val binding: HolderSpellsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderSpellsBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return spellsListCopy.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       val spell: Spells = spellsListCopy[position]
        holder.binding.textView6.text = spell.name
        holder.binding.nameSpells.text = spell.name
        holder.binding.DescriptionSpells.text = spell.description
    }

    fun update(list: List<Spells>?) {
        spellsList.clear()
        if (list != null) {
            spellsList.addAll(list)
        }
        spellsListCopy.clear()
        if (list != null) {
            spellsListCopy.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charFilter = p0.toString()
                if(charFilter.isEmpty()){
                    spellsListCopy = spellsList
                } else {
                    spellsListCopy = spellsList.filter {
                        it.name?.lowercase()!!.contains(charFilter.lowercase())
                    } as ArrayList<Spells>
                }

                val filterResults = FilterResults()
                filterResults.values = spellsListCopy
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                spellsListCopy = p1?.values as ArrayList<Spells>
                notifyDataSetChanged()
            }

        }
    }
}