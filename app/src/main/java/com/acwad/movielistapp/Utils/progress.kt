package com.acwad.movielistapp.Utils

import android.app.ProgressDialog
import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD


object  progress{


    lateinit var hud:KProgressHUD


    fun  showprogress(context:Context){


        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setDetailsLabel("loading data")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();



    }


    fun  dismissprogress(){

        hud.dismiss()
    }
}