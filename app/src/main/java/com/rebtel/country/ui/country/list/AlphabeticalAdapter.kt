package com.rebtel.country.ui.country.list


import android.content.Context
import android.graphics.Outline
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import com.rebtel.country.R
import com.rebtel.country.data.model.ListRow
import com.rebtel.country.util.inflate
import com.rebtel.country.util.toCapitalizeFirstLetterOfEach
import kotlinx.android.synthetic.main.list_item_country.view.*
import timber.log.Timber
import java.io.FileNotFoundException

/**
 * Create adapter for use alphabetical order of country lists
 * @param items
 */
class AlphabeticalAdapter(var items: List<ListRow>) : RecyclerView.Adapter<AlphabeticalAdapter.ViewHolder>(),
    FastScroller.SectionIndexer {

    var onItemClickListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // check the item is in header position
        if (items[position].isHeader) {

        } else {
            // hide the letter index when it's not in header position
            holder!!.itemView.txtLetter.visibility = View.INVISIBLE

            // show scrolling letter
            holder!!.itemView.txtLetterMargin.visibility = View.GONE
        }
        // item at position
        val item = items[position].item

        // bind country name with textview field
        holder!!.itemView.txtName.text =
            (if (!item.name.isNullOrEmpty()) item.name!!.capitalize() else item.name)!!.toCapitalizeFirstLetterOfEach()

        // change the name of file if it has a 'do' key word
        if (item.alpha2Code.equals("do")) {
            item.alpha2Code = "doo"
        }

        if (item.alpha2Code != null) {
            var fileDrawable: Drawable? = getDrawableByName(holder!!.itemView.context, item.alpha2Code)
            holder!!.itemView.iv_item_flag.setImageDrawable(fileDrawable)
            holder!!.itemView.iv_item_flag.scaleType = ImageView.ScaleType.FIT_XY

            // set image corner radius
            val curveRadius = 8F

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                holder!!.itemView.iv_item_flag.outlineProvider = object : ViewOutlineProvider() {

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun getOutline(view: View?, outline: Outline?) {
                        outline?.setRoundRect(0, 0, view!!.width, view?.height, curveRadius)
                    }
                }

                holder!!.itemView.iv_item_flag.clipToOutline = true
            }
        }
    }

    private fun getDrawableByName(context: Context, name: String?): Drawable? {
        try {
            val resources = context.resources
            val resourceId = resources.getIdentifier(
                name?.toLowerCase(), "drawable",
                context.packageName
            )
            if (resourceId > 0) {
                return resources.getDrawable(resourceId)
            } else {
                return null
            }
        } catch (ex: FileNotFoundException) {
            Timber.e("Flag Error", ex.printStackTrace())
            return null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent!!)
        holder.setOnItemClickListener(onItemClickListener!!)
        return holder
    }

    override fun getSectionText(position: Int): String {
        return items[position].char.toString()
    }

    interface OnItemClickListener {
        fun onItemClicked(listRow: ListRow)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.list_item_country)) {
        fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {

            itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(items[adapterPosition])
                }
            }
        }
    }

    fun getItem(pos: Int): ListRow {
        return items[pos]
    }


}