package com.example.googlebooksearchapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlebooksearchapp.R
import com.example.googlebooksearchapp.databinding.BooksSearchResultBinding
import com.example.googlebooksearchapp.model.data.Item


class BookSearchResultAdapter: RecyclerView.Adapter<BookSearchResultAdapter.ViewHolder>() {
    private var bookItems= listOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setBooks(bookItems: List<Item>) {
        this.bookItems = bookItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BooksSearchResultBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookItems[position]

        holder.binding.apply {
            bookTitle.text = book.volumeInfo?.title
            author.text = book.volumeInfo?.authors?.firstOrNull()
            publishedDate.text = book.volumeInfo?.publishedDate

//            Picasso.get()
//                .load(book.volumeInfo?.imageLinks?.smallThumbnail)
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(holder.binding.bookImg)

            if(book.volumeInfo?.imageLinks?.thumbnail != null) {
                Glide.with(holder.itemView.context)
                    .load(book.volumeInfo.imageLinks.thumbnail)
                    .into(holder.binding.bookImg)
            }
        }
    }

    override fun getItemCount() = bookItems.size

    class ViewHolder(val binding: BooksSearchResultBinding): RecyclerView.ViewHolder(binding.root)
}
