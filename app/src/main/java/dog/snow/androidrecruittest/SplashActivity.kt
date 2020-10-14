package dog.snow.androidrecruittest

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import dog.snow.androidrecruittest.databinding.SplashActivityBinding
import dog.snow.androidrecruittest.ui.anim.MotionProgressListener
import dog.snow.androidrecruittest.util.Constants
import dog.snow.androidrecruittest.util.SplashState
import dog.snow.androidrecruittest.viewmodel.SplashViewModel
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel : SplashViewModel by viewModels()
    private lateinit var binding : SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restoreSavedMode()

        binding = SplashActivityBinding.inflate(layoutInflater)

        binding.ivLogoSdSymbol.setImageResource(
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                R.drawable.ic_logo_sd_symbol_dark
            }
            else{
                R.drawable.ic_logo_sd_symbol
            }
        )

        binding.ivLogoSdText.setImageResource(
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                R.drawable.ic_logo_sd_text_dark
            }
            else{
                R.drawable.ic_logo_sd_text
            }
        )

        binding.include.animationView.setAnimation(
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                R.raw.downloading_dark
            }
            else{
                R.raw.downloading
            }
        )


        binding.splashMotionContainer.setTransitionListener(MotionProgressListener { progress: Float ->
            if(progress == 1f){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        setContentView(binding.root)
    }

    private fun restoreSavedMode(){
        Hawk.init(this).build()
        if(!Hawk.contains(Constants.HAWK_MODE_KEY)){
            Hawk.put(Constants.HAWK_MODE_KEY, AppCompatDelegate.MODE_NIGHT_NO)
            return
        }
        val mode = Hawk.get<Int>(Constants.HAWK_MODE_KEY)
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override fun onResume() {
        super.onResume()
        subscribeObservers()
        viewModel.updateCache(this)
    }

    private fun subscribeObservers(){
        viewModel.state.observe(this, Observer {
            binding.include.animationView.isVisible = (it == SplashState.LOADING)
            if(it == SplashState.ERROR){
                showError()
            }
            if(it == SplashState.SUCCESS){
                binding.splashMotionContainer.setTransitionDuration(200)
                binding.splashMotionContainer.transitionToEnd()
            }
        })
    }


    private fun showError() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> viewModel.updateCache(this) }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }

}