package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata.SensorsDataActivity;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SensorsDataFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@ActivityScoped
public class SensorsListFragment extends DaggerFragment implements SensorsListContract.View, SensorClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SensorsAdapter sensorsAdapter;
    private List<SensorDataModel> sensorDataModels;

    @BindView(R.id.rvSensors)
    RecyclerView rvSensors;

    @Inject
    SensorsListContract.Presenter presenter;

    @Inject
    public SensorsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsListFragment newInstance(String param1, String param2) {
        SensorsListFragment fragment = new SensorsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensors_list, container, false);

        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvSensors.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorDataModels = new ArrayList<>();

        sensorsAdapter = new SensorsAdapter(sensorDataModels);
        sensorsAdapter.setSensorClickListener(this);
        rvSensors.setAdapter(sensorsAdapter);
        presenter.takeView(this);
        if (getActivity().getIntent().getExtras() != null) {
            String stationId = getActivity().getIntent().getExtras().getString("stationId", "-1");
            presenter.setStationId(stationId);
            //   presenter.loadSensorsList();
            presenter.loadSensorsList();
        } else {
            // temporary solution
            Toast.makeText(getActivity(), "Not working id", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void stop() {

    }

    @Override
    public void showSensorsList(ArrayList<SensorDataModel> sensorDataModels) {
        sensorsAdapter.updateData(sensorDataModels);
    }

    @Override
    public void showStartSensorsDataActivity(String sensorId) {
        Intent intent = new Intent(getActivity(), SensorsDataActivity.class);
        intent.putExtra("sensorId", sensorId);
        startActivity(intent);
    }

    @Override
    public void onSensorClicked(String sensorId) {
        presenter.startSensorsDataActivity(sensorId);
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

    class SensorsAdapter extends RecyclerView.Adapter<SensorsAdapter.SensorsViewHolder> {

        List<SensorDataModel> sensors;


        SensorClickListener sensorClickListener;

        public SensorsAdapter(List<SensorDataModel> sensors) {
            this.sensors = sensors;
        }

        public class SensorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView tvSensorId;
            private TextView tvSensorValues;

            public SensorsViewHolder(View itemView) {
                super(itemView);

                tvSensorId = (TextView) itemView.findViewById(R.id.tvSensorName);
                tvSensorValues = (TextView) itemView.findViewById(R.id.tvParamValues);

                tvSensorId.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                sensorClickListener.onSensorClicked(sensors.get(getAdapterPosition()).getId());
            }
        }

        private void setSensors(List<SensorDataModel> sensors) {
            this.sensors = sensors;
        }

        public void updateData(List<SensorDataModel> sensorDataModels) {
            setSensors(sensorDataModels);
            notifyDataSetChanged();
        }

        public void setSensorClickListener(SensorClickListener sensorClickListener) {
            this.sensorClickListener = sensorClickListener;
        }

        @Override
        public SensorsAdapter.SensorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor, parent, false);

            SensorsViewHolder sensorsViewHolder = new SensorsViewHolder(view);

            return sensorsViewHolder;


        }

        @Override
        public void onBindViewHolder(SensorsAdapter.SensorsViewHolder holder, int position) {
            holder.tvSensorId.setText((sensors.get(position)).getParamName().trim());

            /**
             *
             * Not gonna work cuz we need a List of values for a particular sensor where
             * id.sensor = values.id etc
             *
             * */
            if (sensors.get(position).getSensorsDataDataModel() != null) {
                Log.e("elo", "not null ->");
                if (sensors.get(position).getSensorsDataDataModel().getValues() != null)
                    holder.tvSensorValues.setText(sensors.get(position).getSensorsDataDataModel().getValues().toString());
            }
        }

        @Override
        public int getItemCount() {
            return sensors.size();
        }

    }

}

interface SensorClickListener {

    void onSensorClicked(String sensorId);

}

