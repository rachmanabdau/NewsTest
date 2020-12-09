package com.rachman.newstest20.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rachman.newstest20.R
import com.rachman.newstest20.databinding.MenuItemBinding
import com.rachman.newstest20.model.MenuItem

class MenuAdapter : RecyclerView.Adapter<MenuViewholder>() {

    private val menuList = listOf(
        MenuItem("Klinik Terdekat", R.drawable.ic_klinik_terdekat),
        MenuItem("Riwayat", R.drawable.ic_riwayat),
        MenuItem("Data Scan", R.drawable.ic_data_scan),
        // Mulai dari sini saya tidak tau cara mengambil icon n ya dari adobe xd
        // sudah saya coba export tapi icon jadi terpisah-pisah
        MenuItem("Notifikasi", R.drawable.ic_klinik_terdekat),
        MenuItem("Beri Nilai", R.drawable.ic_riwayat),
        MenuItem("Pengaturan", R.drawable.ic_data_scan)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewholder {
        val viewBinding =
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewholder(viewBinding)
    }

    override fun onBindViewHolder(holder: MenuViewholder, position: Int) {
        holder.bind(menuList[position])
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}

class MenuViewholder(private val binding: MenuItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MenuItem) {
        val context = binding.root.context
        val menuIcon = ContextCompat.getDrawable(context, item.imagRes)
        binding.menuItem.text = item.name
        binding.menuItem.setCompoundDrawablesWithIntrinsicBounds(null, menuIcon, null, null)
    }
}