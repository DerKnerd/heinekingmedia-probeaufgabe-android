package codes.ulbricht.imanuel.heinekingmediaprobeaufgabe.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import codes.ulbricht.imanuel.heinekingmediaprobeaufgabe.adapter.viewholder.BackgroundsViewHolder
import com.squareup.picasso.Picasso
import java.io.File

class BackgroundsViewAdapter(var images: List<File>) : RecyclerView.Adapter<BackgroundsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundsViewHolder {
        val view = ImageView(parent.context)
        return BackgroundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: BackgroundsViewHolder, position: Int) {
        Picasso
            .get()
            .load(images[position])
            .centerCrop()
            .resize(300, 300)
            .into(holder.imageView)
    }
}