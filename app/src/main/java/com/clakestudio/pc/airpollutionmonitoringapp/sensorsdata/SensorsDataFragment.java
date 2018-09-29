package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import android.content.Context;
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

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.sensors.SensorsListFragment;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SensorsDataFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Binds;

@ActivityScoped
public class SensorsDataFragment extends dagger.android.support.DaggerFragment implements SensorsDataContract.View {

    @BindView(R.id.rvSensorsData)
    RecyclerView rvSensorsData;

    @Inject
    SensorsDataContract.Presenter presenter;
    SensorsDataAdapter sensorsDataAdapter;

    @Inject
    public SensorsDataFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_sensors_data, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        ButterKnife.bind(this, view);
        rvSensorsData.setLayoutManager(linearLayoutManager);


        return view;
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
        sensorsDataAdapter.updateData(sensorsDataDataModels);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SensorsDataDataModel> sensorsDataList = new ArrayList<>();

        sensorsDataAdapter = new SensorsDataAdapter(sensorsDataList);
        rvSensorsData.setAdapter(sensorsDataAdapter);

        presenter.takeView(this);

        if (getActivity().getIntent().getExtras() != null) {
            String sensorId = getActivity().getIntent().getExtras().getString("sensorId", "-1");
            presenter.loadSensorsData(sensorId);
        }
    }

    class SensorsDataAdapter extends RecyclerView.Adapter<SensorsDataAdapter.ViewHolder> {


        ArrayList<SensorsDataDataModel> sensorsDataDataModelList;


        public SensorsDataAdapter(ArrayList<SensorsDataDataModel> sensorsDataDataModelList) {
            this.sensorsDataDataModelList = sensorsDataDataModelList;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvKey;
            TextView tvValues;

            public ViewHolder(View itemView) {
                super(itemView);

                tvKey = (TextView) itemView.findViewById(R.id.tvSensorKey);
                tvValues = (TextView) itemView.findViewById(R.id.tvValues);

            }
        }

        @Override
        public SensorsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensors_data, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(SensorsDataAdapter.ViewHolder holder, int position) {
            holder.tvKey.setText(sensorsDataDataModelList.get(position).getKey());

            /**
             *
             * Here we should not format the data but I could not find better place.
             *
             * */

            holder.tvValues.setText(SensorsDataFormatter.formatToString(sensorsDataDataModelList.get(position).getValues().toString()));
        }

        @Override
        public int getItemCount() {
            return sensorsDataDataModelList.size();
        }

        public void setSensorsDataDataModelList(ArrayList<SensorsDataDataModel> sensorsDataDataModelList) {
            this.sensorsDataDataModelList = sensorsDataDataModelList;
        }

        public void updateData(ArrayList<SensorsDataDataModel> sensorsDataDataModelList) {
            setSensorsDataDataModelList(sensorsDataDataModelList);
            notifyDataSetChanged();
        }

    }
}
