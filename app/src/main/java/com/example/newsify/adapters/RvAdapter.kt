import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.newsify.R
import com.example.newsify.model.api.NewsItemRetrofit


class RvAdapter(val context: Context?, private val articles: List<NewsItemRetrofit>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.news_item_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = articles[position]
        holder.source.text = article.source.name
        holder.news_genre.text = article.author
        holder.title.text = article.title
        Glide.with(context!!)
            .load(article.urlToImage)
            .into(holder.newsImage)

        holder.newsImage.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse(article.url)
            context.startActivity(urlIntent)

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return articles.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val source: TextView = itemView.findViewById(R.id.source)
        val news_genre: TextView = itemView.findViewById(R.id.news_genre)
        val title: TextView = itemView.findViewById(R.id.title)
        val newsImage: ImageButton = itemView.findViewById(R.id.news_img)
    }
}
