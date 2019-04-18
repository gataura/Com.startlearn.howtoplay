package poker.comstartlearnhowtoplay.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.google.firebase.database.DataSnapshot
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import poker.comstartlearnhowtoplay._core.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import poker.comstartlearnhowtoplay.*


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 */
class SplashActivity : BaseActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private lateinit var dataSnapshot: DataSnapshot

    var gclid: String? = null

    override fun getContentView(): Int = R.layout.activity_web_view

    override fun initUI() {
        webView = web_view
        progressBar = progress_bar
        val config = YandexMetricaConfig.newConfigBuilder("4ca195b9-3251-4e5a-8bdc-a3e797c31b84").build()
        YandexMetrica.activate(this, config)
        YandexMetrica.enableActivityAutoTracking(this.application)
    }

    fun getPreferer(context: Context): String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        if (!sp.contains(REFERRER_DATA)) {
            return "Didn't got any referrer follow instructions"
        }
        return sp.getString(REFERRER_DATA, null)
    }

    fun getGclid(){
        if (gclid != null) {
            if (gclid!!.contains("gclid")) {
                gclid = gclid?.substringAfter("gclid=")
                gclid = gclid?.substringBefore("&conv")
            } else {
                gclid = null
            }
        }
    }

    override fun setUI() {
        logEvent("splash-screen")
        webView.webViewClient = object : WebViewClient() {
            /**
             * Check if url contains key words:
             * /money - needed user (launch WebViewActivity or show in browser)
             * /main - bot or unsuitable user (launch ContentActivity)
             */
            @SuppressLint("deprecated")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("/money")) {
                    // task url for web view or browser
                    val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    val value = dataSnapshot.child(SHOW_IN).value as String

                    if (value == WEB_VIEW) {
                        if ((gclid != null) && (gclid != "")) {
                            startActivity(
                                    Intent(this@SplashActivity, WebViewActivity::class.java)
                                            .putExtra(EXTRA_TASK_URL, "$taskUrl?gclid=$gclid")
                            )
                        } else{
                            startActivity(
                                    Intent(this@SplashActivity, WebViewActivity::class.java)
                                            .putExtra(EXTRA_TASK_URL, taskUrl)
                            )
                        }
                        finish()
                    } else if (value == BROWSER) {
                        // launch browser with task url
                        val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("")
                        )

                        logEvent("task-url-browser")
                        startActivity(browserIntent)
                        finish()
                    }
                } else if (url.contains("/main")) {
                    //val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
                progressBar.visibility = View.GONE
                return false
            }
        }

        progressBar.visibility = View.VISIBLE

        if (getPreferer(this) != "Didn't got any referrer follow instructions") {
            gclid = getPreferer(this)
        } else {
            gclid = null
        }
        getGclid()

        Log.d("testest", getPreferer(this))


        getValuesFromDatabase({
            dataSnapshot = it
            // load needed url to determine if user is suitable
            webView.loadUrl(it.child(SPLASH_URL).value as String)
        }, {
            Log.d("SplashErrActivity", "didn't work fetchremote")
            progressBar.visibility = View.GONE
        })
    }
}