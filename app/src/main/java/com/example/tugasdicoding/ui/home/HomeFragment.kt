package com.example.tugasdicoding.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasdicoding.Negara
import com.example.tugasdicoding.R
import com.example.tugasdicoding.databinding.FragmentHomeBinding
import com.example.tugasdicoding.ListNegaraAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvNegaras: RecyclerView
    private lateinit var listNegaraAdapter: ListNegaraAdapter
    private val listNegara = arrayListOf<Negara>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNegaras = binding.rvNegaras
        rvNegaras.setHasFixedSize(true)

        // Inisialisasi data dan adapter
        listNegara.addAll(getNegaraList()) // Fungsi untuk mengambil data list negara
        listNegaraAdapter = ListNegaraAdapter(listNegara)

        rvNegaras.layoutManager = LinearLayoutManager(requireContext())
        rvNegaras.adapter = listNegaraAdapter
    }

    private fun getNegaraList(): ArrayList<Negara> {
        val negaraNames = resources.getStringArray(R.array.data_name)
        val negaraDescriptions = resources.getStringArray(R.array.data_description)
        val negaraLongDescriptions = resources.getStringArray(R.array.data_long_description)
        val negaraWaktuGabung = resources.getStringArray(R.array.data_waktu_gabung) // Tambahkan
        val negaraLuasWilayah = resources.getStringArray(R.array.data_luas_wilayah) // Tambahkan
        val negaraPhotos = resources.obtainTypedArray(R.array.data_photo)

        val list = ArrayList<Negara>()
        for (i in negaraNames.indices) {
            val negara = Negara(
                negaraNames[i],
                negaraDescriptions[i],
                negaraPhotos.getResourceId(i, -1),
                negaraLongDescriptions[i],
                negaraWaktuGabung[i], // Tambahkan data waktu_gabung
                negaraLuasWilayah[i]  // Tambahkan data luas_wilayah
            )
            list.add(negara)
        }
        negaraPhotos.recycle()
        return list
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
