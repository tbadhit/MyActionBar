package com.tbadhit.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView

// Tambahkanlah FrameLayout pada main_activity.xml (1)
// Buat berkas xml baru pada directory menu dengan nama berkas "option_menu.xml" (klik kanan pada res → values → new → Android resource file)
// create icon (klik kanan pada folder drawable → New → Vector Asset → Pada Clipart cari "announcement" → Ganti Color jadi putih → Next → Finish.)
// update code "option_menu.xml" (1)
// create new activity "MenuActivity"
// update code "activtiy_menu.xml" (1)
// create new fragment "MenuFragment"
// update code "fragment_menu.xml" (1)
// add code "MainActivity" (1)

// SearchView on Action Bar :
// create icon (klik kanan pada folder drawable → New → Vector Asset → Pada Clipart cari "search" → Ganti Color jadi putih → Next → Finish.)
// update "option_menu.xml" (add SearchView) (2)
// add code "MainActivity" (2)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // (1)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        // (2)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        /*
        (menu?.findItem(R.id.search)?.actionView) = Untuk mengambil komponen searchview
         yang sebelumnya sudah di inflate
         */
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        //-----

        // (2)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        /*
        (queryHint) = berguna untuk memberikan hint pada pengguna tentang query search apa
        yang telah dimasukkan. Hal ini akan memudahkan pengguna untuk memasukkan suatu kata.
         */
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
            "Metode onQueryTextSubmit() akan dipanggil saat Submit ditekan"
            */
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
            "onQueryTextChange() akan dipanggil setiap kali user memasukkan atau
            mengubah query yang ada pada inputan searchview"
            */
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        //-----

        return true
    }

    // (1)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // (1)
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }

            // (1)
            R.id.menu2 -> {
                val i = Intent(this, MenuActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}