package com.example.tugasdicoding

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    private var negara: Negara? = null // Variable to store negara object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the country data passed from the intent
        negara = intent.getParcelableExtra("EXTRA_NEGARA")

        if (negara != null) {
            // Set the data into the views
            findViewById<TextView>(R.id.tvDetailName).text = negara!!.name
            findViewById<TextView>(R.id.tvDetailDescription).text = negara!!.longDescription
            findViewById<ImageView>(R.id.imgDetailPhoto).setImageResource(negara!!.photo)
            findViewById<TextView>(R.id.waktu_gabung).text = negara!!.waktuGabung
            findViewById<TextView>(R.id.luas_wilayah).text = negara!!.luasWilayah
        }
    }

    // Inflate menu for the activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    // Handle menu item click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            shareCountryDetails() // Call the share function
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Function to share country details
    private fun shareCountryDetails() {
        negara?.let {
            val shareText = """
                Nama Negara: ${it.name}
                Deskripsi: ${it.longDescription}
                Waktu Bergabung: ${it.waktuGabung}
                Luas Wilayah: ${it.luasWilayah}
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
        }
    }
}
