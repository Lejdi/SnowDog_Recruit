package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import dagger.hilt.android.AndroidEntryPoint
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.DetailsFragmentBinding
import dog.snow.androidrecruittest.ui.util.Constants
import dog.snow.androidrecruittest.viewmodel.DetailsViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment(){
    private lateinit var binding: DetailsFragmentBinding
    private val viewModel : DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        subscribeObservers()
        if(arguments!=null){
            val id = arguments?.getInt(Constants.KEY_SELECTED_ITEM_ID)
            if(id!=null){
                viewModel.retrieveData(id)
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.selectedItem.observe(this, Observer {
            if(it!=null){
                val url = GlideUrl(
                    it.url, LazyHeaders.Builder()
                        .addHeader("User-Agent", "Lejdi")
                        .build()
                )
                val placeholder = if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    R.drawable.ic_placeholder_dark
                }
                else{
                    R.drawable.ic_placeholder
                }
                Glide.with(binding.root).asBitmap().load(url).placeholder(placeholder).into(binding.ivPhoto)
                binding.tvPhotoTitle.text = it.photoTitle
                binding.tvAlbumTitle.text = it.albumTitle
                binding.tvUsername.text = it.username
                binding.tvEmail.text = it.email
                binding.tvPhone.text = it.phone
            }
        })
    }

}