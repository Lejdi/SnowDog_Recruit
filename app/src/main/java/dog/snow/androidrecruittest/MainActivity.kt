package dog.snow.androidrecruittest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import dog.snow.androidrecruittest.databinding.MainActivityBinding
import dog.snow.androidrecruittest.util.Constants
import dog.snow.androidrecruittest.util.NetworkTools
import kotlinx.android.synthetic.main.layout_banner.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_down, R.anim.no_animation)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        setChangeThemeButton()

    }

    override fun onStart() {
        super.onStart()

        NetworkTools.networkState.observe(this, Observer {
            binding.root.banner.isVisible = !it
        })

        NetworkTools.registerNetworkCallbacks(this)
    }

    private fun setChangeThemeButton(){
        binding.includeAppbar.appbar.btn_switch_mode.setOnClickListener {
            Hawk.init(this).build()
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Hawk.put(Constants.HAWK_MODE_KEY, AppCompatDelegate.MODE_NIGHT_NO)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Hawk.put(Constants.HAWK_MODE_KEY, AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        NetworkTools.unregisterNetworkCallbacks(this)
    }
}