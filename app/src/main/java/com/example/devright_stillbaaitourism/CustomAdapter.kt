import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.devright_stillbaaitourism.BusinessData
import com.example.devright_stillbaaitourism.EatData
import com.squareup.picasso.Picasso
import com.example.devright_stillbaaitourism.R

class CustomAdapter (private val context: Context, private var eatDataList: List<EatData>) : BaseAdapter() {

    override fun getCount(): Int {
        return eatDataList.size
    }

    override fun getItem(position: Int): EatData {
        return eatDataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        // Initializing views (list_item)
        val eatData = getItem(position)
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView?.findViewById<TextView>(R.id.titleTextView)
        val contactTextView = itemView?.findViewById<TextView>(R.id.tvContactNum)
        val locationToEats = itemView?.findViewById<TextView>(R.id.tvLocationToEats)

        val defaultImageResource = R.drawable.no_image


        // Loading images into imageView
        if (eatData.EAT_IMAGE_URLS.isNotEmpty()) {
            val imageUrl = eatData.EAT_IMAGE_URLS[0]
            Picasso.get().load(imageUrl).into(imageView)
        } else {
            imageView?.setImageResource(defaultImageResource)
        }

        // Settings title text
        titleTextView?.text = eatData.EAT_NAME

        // Setting location
        locationToEats?.text = eatData.EAT_ADDRESS


        // Displaying a mobile or tell number, whichever exists
        if(!eatData.EAT_MOBILE_NUM.isNullOrBlank()){
            contactTextView?.text = eatData.EAT_MOBILE_NUM
        }else if(!eatData.EAT_TEL_NUM.isNullOrBlank()){
            contactTextView?.text = eatData.EAT_TEL_NUM
        }else{
            contactTextView?.visibility = View.GONE;
        }

        return itemView ?: View(context)
    }

    fun updateData(newList: List<EatData>) {
        eatDataList = newList.toMutableList()
        notifyDataSetChanged()
    }

}
