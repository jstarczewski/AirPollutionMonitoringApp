package com.clakestudio.pc.airpollutionmonitoringapp.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jan on 9/17/2018.
 */

public class BaseActivity extends AppCompatActivity {

    static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int fragmentFrameId) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentFrameId, fragment);
        fragmentTransaction.commit();

    }


}
