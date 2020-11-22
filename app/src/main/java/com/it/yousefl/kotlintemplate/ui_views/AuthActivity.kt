package com.it.yousefl.kotlintemplate.ui_views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.it.yousefl.kotlintemplate.R
import com.it.yousefl.kotlintemplate.databinding.ActivityAuthBinding
import com.it.yousefl.kotlintemplate.utils.AuthResource
import com.it.yousefl.kotlintemplate.utils.Constants
import com.it.yousefl.kotlintemplate.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() ,View.OnClickListener{

      val TAG = "AuthActivity"
    lateinit var binding:ActivityAuthBinding

    private val viewModel: AuthViewModel by viewModels()
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_auth)

        binding.btn.setOnClickListener(this)

        if (Utils.getValue(this,Constants.IS_REGISTERED,false)){
            Utils.goToActivity(this,MainActivity::class.java,true)
        }


    }
    @ExperimentalStdlibApi
    override fun onClick(p0: View?) {
        subscribeObservers()
        viewModel.setStateEvent(AuthStateevent.GetBlogsEvent,et_username.text.toString(),et_password.text.toString())
    }
    private fun subscribeObservers(){
        viewModel.authState.observe(this, Observer { dataState ->
            when (dataState.status) {
                AuthResource.AuthStatus.LOADING -> {

                  //      showLoading()

                    Log.d(TAG, "onChanged: LOADING")
                }
                AuthResource.AuthStatus.AUTHENTICATED -> {
                  //  hideLoading()
                    Log.d(TAG, "onChanged: AUTHENTICATED")
                    Log.d(TAG, "onChanged: data= " + dataState.data)
                    if (dataState.data?.id !== 0 && dataState.data != null) {
                        Utils.setValue(
                            this@AuthActivity,
                            Constants.ID,
                            dataState.data.id
                        )
                        Utils.setValue(this,Constants.IS_REGISTERED,true)
                        Utils.goToActivityWithAnimation(
                            this@AuthActivity,
                            MainActivity::class.java,
                            true
                        )
                    } else {
                        Utils.getDialog(this@AuthActivity, "", "Wrong password or username !!!")
                    }
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                //    hideLoading()
                    Log.d(TAG, "onChanged: NOT_AUTHENTICATED")
                }
                AuthResource.AuthStatus.ERROR -> {
                //    hideLoading()
                    Log.d(TAG, "onChanged: ERROR")
                }
            }


        })
    }


}