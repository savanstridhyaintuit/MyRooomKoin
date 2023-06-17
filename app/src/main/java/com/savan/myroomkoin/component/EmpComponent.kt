package com.savan.myroomkoin.component

import com.savan.myroomkoin.utils.NetworkHelper
import com.savan.myroomkoin.viewmodel.EmpViewModel
import com.savan.myroomkoin.viewmodel.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EmpComponent : KoinComponent {

    val empViewModel: EmpViewModel by inject()
    val mainViewModel: MainViewModel by inject()
    val isNetworkHelper:NetworkHelper by inject()

}