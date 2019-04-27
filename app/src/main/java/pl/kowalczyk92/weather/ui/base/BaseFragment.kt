package pl.kowalczyk92.weather.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), BaseView {

    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? = inflater.inflate(layout, container, false)

    override fun showMessage(resId: Int) {
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show()
    }
}