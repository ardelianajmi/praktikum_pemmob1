package com.example.pemmobpertama

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pemmobpertama.data.model.BookDoc
import com.example.pemmobpertama.databinding.ActivityDaftarBukuBinding
import com.example.pemmobpertama.ui.fragment.BookDetailFragment
import com.example.pemmobpertama.viewmodel.MainViewModel
import com.unsoed.informatikamobile.adapter.BookAdapter

class DaftarBukuActivity : AppCompatActivity(), BookAdapter.OnBookClickListener {
    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) {
            adapter.setData(it)
        }
        viewModel.fetchBooks(query = "kotlin programming")
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b->
            BookDetailFragment(
                b.title?:"-",
                b.authorName?.joinToString(", ") ?: "-",
                b.firstPublishYear?.toString() ?: "-",
                b.coverId ?: 0
            ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)

        }
    }
}