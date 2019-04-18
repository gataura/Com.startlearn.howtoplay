package poker.comstartlearnhowtoplay.ui


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.google.firebase.analytics.FirebaseAnalytics
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_web_view.*
import poker.comstartlearnhowtoplay.*
import poker.comstartlearnhowtoplay._core.BaseActivity


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 */
class DeepLinkWebView : BaseActivity(), AdvancedWebView.Listener {

    private lateinit var webView: AdvancedWebView
    private lateinit var progressBar: ProgressBar
    var gclid: String? = null
    lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var prefs: SharedPreferences

    override fun getContentView(): Int = R.layout.activity_web_view


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

    override fun initUI() {
        webView = web_view
        progressBar = progress_bar
        prefs = getSharedPreferences("com.datingonline.meet", Context.MODE_PRIVATE)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        if (getPreferer(this) != "Didn't got any referrer follow instructions") {
            gclid = getPreferer(this)
        } else {
            gclid = null
        }
    }


    private var conversions: MutableList<Conversion> = mutableListOf()
    override fun setUI() {

        getValuesFromDatabase({ dataSnapshot ->
            val values = dataSnapshot.child(CONVERSION_DATA)
            for (conversionSnapshot in values.children) {
                val conversion = conversionSnapshot.getValue(Conversion::class.java)
                conversion?.conversionEvent = conversionSnapshot.key!!
                conversion?.let { conversions.add(it) }
            }
            webView.loadUrl(intent?.data.toString())
        })

        logEvent("web-view-screen")
        progressBar.visibility = View.VISIBLE

        configureWebView()

    }

    private fun logEventIfUrlIsSuitable(urlSafe: String) {
        conversions.forEach {
            if (urlSafe.contains(it.offerId!!) && (urlSafe.contains(it.l!!))) {
                val uri = Uri.parse(urlSafe)
                val args = uri.queryParameterNames
                val bundle = Bundle()

                args.forEach {key ->
                    bundle.putString(key, uri.getQueryParameter(key))
                }
                logEvent(it.conversionEvent!!, bundle)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                url?.let {urlSafe->
                    logEventIfUrlIsSuitable(urlSafe)
                }
                progressBar.visibility = View.GONE
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        if (prefs.getBoolean("firstrun",true)) {
            val bundle = Bundle()
            getGclid()
            if (gclid != null) {
                bundle.putString("gclid", gclid)
            } else {
                bundle.putString("gclid", "")
            }

            firebaseAnalytics.logEvent("reg_open", bundle)
            prefs.edit().putBoolean("firstrun", false).apply()
        }

    }

    override fun onBackPressed() {
        if (!webView.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        webView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        webView.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webView.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
            url: String?,
            suggestedFilename: String?,
            mimeType: String?,
            contentLength: Long,
            contentDisposition: String?,
            userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }
}