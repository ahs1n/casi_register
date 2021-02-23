package edu.aku.hassannaqvi.casi_register.ui.other

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.SplashscreenViewModel
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.ui.login_activity.LoginActivity
import edu.aku.hassannaqvi.casi_register.utils.gotoActivity
import edu.aku.hassannaqvi.casi_register.utils.obtainViewModel
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import kotlinx.android.synthetic.main.activity_splashscreen.*
import kotlinx.coroutines.*
import java.io.InputStreamReader

/*
* @author Ali Azaz Alam dt. 12.16.20
* */
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var activityScope: Job
    lateinit var viewModel: SplashscreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        activityScope = launchSplashScope()
        viewModel = obtainViewModel(SplashscreenViewModel::class.java, GeneralRepository(DatabaseHelper(this)))

        /*
        * Show FullScreen
        * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) window.insetsController?.hide(WindowInsets.Type.statusBars())
        else window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        /*
        * Observe zscore count
        * */
        viewModel.zscoreRecord.observe(this, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    finish()
                    gotoActivity(LoginActivity::class.java)
                }
                ResponseStatus.ERROR -> {
                    Snackbar.make(findViewById(android.R.id.content), it.data.toString(), Snackbar.LENGTH_LONG).show()
                }
                ResponseStatus.LOADING -> {
                    progressSplashscreen.visibility = View.VISIBLE
                    Snackbar.make(findViewById(android.R.id.content), "Configuring ZScore. Don't close app", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (activityScope.isActive.not())
            launchSplashScope()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

    private fun launchSplashScope() =
            CoroutineScope(Dispatchers.Main).launch {
                delay(SPLASH_TIME_OUT.toLong())
                if (SharedStorage.getFirstInstallFlag(this@SplashscreenActivity)) {
                    SharedStorage.setFirstInstallFlag(this@SplashscreenActivity, false)
                    viewModel.insertZScoreRecord(InputStreamReader(assets.open("zStandardJson.json")))
                } else {
                    finish()
                    gotoActivity(LoginActivity::class.java)
                }
            }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }
}