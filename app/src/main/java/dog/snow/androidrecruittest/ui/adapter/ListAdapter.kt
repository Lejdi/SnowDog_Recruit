package dog.snow.androidrecruittest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.ListItemBinding
import dog.snow.androidrecruittest.ui.ListFragment
import dog.snow.androidrecruittest.ui.model.ListItem
import dog.snow.androidrecruittest.viewmodel.ListViewModel


class ListAdapter constructor(
    private val viewModel: ListViewModel,
    private val mListener: OnListFragmentInteractionListener
)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val mValues = MutableLiveData<List<ListItem>>()
    private lateinit var binding: ListItemBinding

    init{
        viewModel.items.observe(mListener as ListFragment, Observer {
            mValues.value = it.filter { item -> (item.title.contains(viewModel.filterString.value.toString()) || item.albumTitle.contains(viewModel.filterString.value.toString())) }
            viewModel.displayedItemsCount.value = mValues.value?.size
            notifyDataSetChanged()
        })
        viewModel.filterString.observe(mListener, Observer { filter ->
            mValues.value = viewModel.items.value?.filter { item -> (item.title.contains(filter) || item.albumTitle.contains(filter)) }
            viewModel.displayedItemsCount.value = mValues.value?.size
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues.value!![position]
        holder.mItem = item
        holder.photoTitle.text = item.title
        holder.albumTitle.text = item.albumTitle
        val url = GlideUrl(
            item.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "Lejdi")
                .build()
        )
        val placeholder = if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            R.drawable.ic_placeholder_dark
        }
        else{
            R.drawable.ic_placeholder
        }
        Glide.with(binding.root).asBitmap().load(url).placeholder(placeholder).into(holder.thumbnail)
        holder.binding.root.setOnClickListener {
            mListener.onListFragmentClickInteraction(holder.mItem!!, position)
        }
    }

    override fun getItemCount(): Int {
        if(mValues.value?.size == null)
            return 0
        return mValues.value?.size!!
    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val photoTitle = this.binding.tvPhotoTitle
        val albumTitle = this.binding.tvAlbumTitle
        val thumbnail = this.binding.ivThumb
        var mItem: ListItem? = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentClickInteraction(listItem: ListItem, position: Int)
    }
}