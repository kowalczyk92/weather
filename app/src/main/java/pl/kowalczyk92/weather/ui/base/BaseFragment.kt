package pl.kowalczyk92.weather.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasSupportFragmentInjector, BaseView {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    abstract val layout: Int

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? = inflater.inflate(layout, container, false)

    override fun showMessage(resId: Int) {
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    protected open fun inject() = AndroidSupportInjection.inject(this)
}