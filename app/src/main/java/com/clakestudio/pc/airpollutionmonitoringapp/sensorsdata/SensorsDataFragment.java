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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Binds;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorsDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorsDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@ActivityScoped
public class SensorsDataFragment extends dagger.android.support.DaggerFragment implements SensorsDataContract.View {
    // TODO: Rename parameter arguments, choose names that match
    private ArrayList<SensorsDataDataModel> sensorsDataList;

    @BindView(R.id.rvSensorsData)
    RecyclerView rvSensorsData;

    @Inject
    SensorsDataContract.Presenter presenter;
    SensorsDataAdapter sensorsDataAdapter;

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
        sensorsDataDataModels.add(new SensorsDataDataModel("elo0"));
        sensorsDataAdapter.updateData(sensorsDataDataModels);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorsDataList = new ArrayList<>();

        sensorsDataAdapter = new SensorsDataAdapter(sensorsDataList);
        rvSensorsData.setAdapter(sensorsDataAdapter);

        presenter.takeView(this);

        if (getActivity().getIntent().getExtras() != null) {
            String sensorId = getActivity().getIntent().getExtras().getString("sensorId", "-1");
            Log.e("id", sensorId);
            presenter.loadSensorsData(sensorId);
        }
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
       //     holder.tvValues.setText(sensorsDataDataModelList.get(position).getValues().toString());
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
