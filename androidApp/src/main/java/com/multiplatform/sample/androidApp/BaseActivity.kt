package com.multiplatform.sample.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dev.icerock.moko.mvvm.MvvmActivity
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class BaseActivity<B : ViewDataBinding, C : ViewModel> : MvvmActivity<B, C>() {

}