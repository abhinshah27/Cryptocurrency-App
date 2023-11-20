package my.demo.myapplication.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.databinding.BindingAdapter
import coil.load
import java.util.Locale
import my.demo.myapplication.R


/**
 * Checks if the device has an active internet connection.
 *
 * @param context The application context.
 * @return True if the device has internet connectivity, false otherwise.
 */
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

/**
 * Binding adapter method to load an image into an ImageView using the Coil library.
 *
 * @param imgUrl The URL of the image to be loaded.
 */
@BindingAdapter("setLogo")
fun ImageView.setLogo(imgUrl: String?) {
    this.let { imageView ->
        imgUrl?.let {
            imageView.load(it) {
                placeholder(
                    ColorDrawable(
                        ContextCompat.getColor(
                            imageView.context,
                            R.color.color_gray
                        )
                    )
                )
            }
        }
    }
}

/**
 * Binding adapter method to set the text and color of a TextView based on a double value.
 *
 * @param doubleValue The double value to be displayed.
 */
@BindingAdapter("setPercentChange")
fun TextView.setPercentChange(doubleValue: Double?) {
    doubleValue?.let {
        val format = String.format(Locale.US, "%.2f", it)
        text = if (it > 0) {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.green))
            "+$format"
        } else {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.red))
            format
        }
    }
}
/**
 * Binding adapter method to set the text of a TextView with a formatted price string.
 *
 * @param doubleValue The double value representing the price.
 */
@SuppressLint("SetTextI18n")
@BindingAdapter("setPrice")
fun TextView.setPrice(doubleValue: Double?) {
    doubleValue?.let {
        text = "$${String.format(Locale.US, "%.2f", it)} USD"
    }
}
/**
 * Binding adapter method to set the image resource of an ImageView based on a boolean flag.
 *
 * @param isPositive A boolean flag indicating whether the image should represent a positive change.
 */

@BindingAdapter("setChangesImage")
fun ImageView.setChangesImage(isPositive: Boolean) {
    if (isPositive) {
        this.setImageResource(R.drawable.ic_positive)
    } else {
        this.setImageResource(R.drawable.ic_negative)
    }
}
/**
 * Displays a toast message in the given context.
 *
 * @param msg The message to be displayed in the toast.
 * @param context The context in which the toast should be displayed.
 * @return The created Toast object or null if the method is not called on the main thread.
 */

fun showToast(msg: String?, context: Context): Toast? {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        return null
    }
    if (!TextUtils.isEmpty(msg)) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.show()
        return toast
    }
    return null
}
/**
 * Shows the view by setting its visibility to VISIBLE.
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Hides the view by setting its visibility to GONE.
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * Sets the status bar color and appearance for the MainActivity.
 *
 * This method is responsible for configuring the status bar color and appearance
 * based on the requirements of the application.
 */
fun Activity.setStatusBarColorAndAppearance() {
    try {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}









