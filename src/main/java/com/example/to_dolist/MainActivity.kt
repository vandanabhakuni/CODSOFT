package com.example.to_dolist

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : ComponentActivity() {
    private lateinit var add: Button
    private lateinit var clear: Button
    private lateinit var listView: ListView
    private lateinit var delete: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.add)
        clear = findViewById(R.id.clear)
        listView = findViewById(R.id.listView)
        delete = findViewById(R.id.delete)
        editText = findViewById(R.id.editText)

        var itemlist = arrayListOf<String>()
        var adapter =ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        add.setOnClickListener {
            itemlist.add(editText.text.toString())
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}