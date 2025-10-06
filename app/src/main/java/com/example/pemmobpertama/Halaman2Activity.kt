package com.example.pemmobpertama

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.pemmobpertama.databinding.ActivityHalaman2Binding

class Halaman2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHalaman2Binding

    private val latitude = "-7.429427"
    private val longitude = "109.338082"
    private val gMapsUrl = "http://maps.google.com/maps?q=loc:"
    private val packageMaps = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHalaman2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
    }

    private fun initLayout() {
        binding.layoutLocation.imgIcon.setImageResource(R.drawable.ic_location)
        binding.layoutLocation.tvLayout.setText(R.string.alamat)

        binding.layoutEmail.imgIcon.setImageResource(R.drawable.ic_email)
        binding.layoutEmail.tvLayout.setText(R.string.email)

        binding.layoutIg.imgIcon.setImageResource(R.drawable.ic_himpunan)
        binding.layoutIg.tvLayout.setText(R.string.ig_himpunan)

        binding.layoutPhone.imgIcon.setImageResource(R.drawable.ic_phone)
        binding.layoutPhone.tvLayout.setText(R.string.telepon)

        binding.layoutBuku.let {
            it.imgIcon.setImageResource(R.drawable.ic_book)
            it.tvLayout.setText(R.string.koleksi_buku)
        }
    }

    private fun initListener() {
        // buka Google Maps
        binding.layoutLocation.root.setOnClickListener {
            val gMapsIntentUrl = "$gMapsUrl$latitude,$longitude".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gMapsIntentUrl)
            startActivity(mapIntent.setPackage(packageMaps))
        }

        // buka Instagram
        binding.layoutIg.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = getString(R.string.ig_himpunan).toUri()
            startActivity(intent)
        }

        binding.layoutBuku.root.setOnClickListener {
            startActivity(Intent(this, DaftarBukuActivity::class.java))
        }

        // kirim email
        binding.layoutEmail.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:${getString(R.string.email)}".toUri()
            }
            startActivity(intent)
        }

        // panggil telepon
        binding.layoutPhone.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:${getString(R.string.telepon)}".toUri()
            }
            startActivity(intent)
        }

        binding.layoutBuku.root.setOnClickListener {
            startActivity(Intent(this, DaftarBukuActivity::class.java))
        }

        // tombol back
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
