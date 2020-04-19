package com.cherifi.acnhcheatsgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cherifi.acnhcheatsgenerator.data.Cheat
import com.cherifi.acnhcheatsgenerator.data.CheatList
import com.cherifi.acnhcheatsgenerator.fragments.ItemFragment
import com.cherifi.acnhcheatsgenerator.fragments.RecipeFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), RecipeFragment.OnListFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        NavigationUI.setupWithNavController(bottomNavigationView, nav_host_fragment.findNavController())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return true
    }

    override fun onListFragmentInteraction(item: Pair<String, String>) {
        val id = item.second
        val name = item.first
        CheatList.list.add(Cheat(id, name))
        Toast.makeText(this, "L'objet $id - $name a été ajouté. ", Toast.LENGTH_SHORT).show()
    }
}
