package layout

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlistapp.Product
import com.example.wishlistapp.R


class ProductAdapter (private val products:MutableList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var nameTextView:TextView =itemView.findViewById(R.id.productTv)
        var priceTextView:TextView =itemView.findViewById(R.id.priceTv)
        var urlTextView:TextView =itemView.findViewById(R.id.urlTv)


//        init {
//            nameTextView=itemView.findViewById(R.id.productTv)
//            priceTextView=itemView.findViewById(R.id.priceTv)
//            urlTextView=itemView.findViewById(R.id.urlTv)
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.product_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product=products[position]

        holder.nameTextView.text=product.name
        holder.priceTextView.text=product.price
        holder.urlTextView.text=product.url

        holder.itemView.setOnClickListener{
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(product.url))
                ContextCompat.startActivity(holder.itemView.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(holder.itemView.context, "Invalid URL for " + product.name, Toast.LENGTH_LONG).show()
            }
        }

        holder.itemView.setOnLongClickListener{
            products.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            Toast.makeText(holder.itemView.context,"Item removed,",Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}