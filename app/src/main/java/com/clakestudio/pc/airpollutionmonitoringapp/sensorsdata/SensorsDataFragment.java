package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorsDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorsDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@ActivityScoped
public class SensorsDataFragment extends DaggerFragment implements SensorsDataContract.View {
    // TODO: Rename parameter arguments, choose names that match


    private List<SensorsDataDataModel> sensorsDataList;



    @Inject
    SensorsDataContract.Presenter presenter;


    @Inject
    public SensorsDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsDataFragment newInstance(String param1, String param2) {
        SensorsDataFragment fragment = new SensorsDataFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors_data, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void stop() {

    }

    @Override
    public void showSensorsData(ArrayList<SensorsDataDataModel> sensorsDataDataModels) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
