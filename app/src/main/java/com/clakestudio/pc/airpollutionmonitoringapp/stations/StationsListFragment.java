package com.clakestudio.pc.airpollutionmonitoringapp.stations;

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

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.sensors.SensorsListActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StationsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StationsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@ActivityScoped
public class StationsListFragment extends DaggerFragment implements StationsListContract.View {

    List<StationDataModel> stationDataModelList;
    StationsAdapter stationsAdapter;

    @BindView(R.id.rvStations)
    RecyclerView rvStations;

    @Inject
    StationsListContract.Presenter presenter;

    private OnFragmentInteractionListener mListener;

    @Inject
    public StationsListFragment() {
        // Required empty public constructor
    }

    public static StationsListFragment newInstance() {
        return new StationsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations_list, container, false);

        stationDataModelList = new ArrayList<>();

        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvStations.setLayoutManager(linearLayoutManager);


        return view;
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

    /**
     * The difference with MVP with Dagger 2 and without Dagger 2 according to Google
     * Architecture samples is that we setPresenter in Fragment when we are not using Dagger
     * and we set View prom presenter when we are using Dagger
     */

/*
    @Override
    public void setPresenter(StationsListContract.Presenter presenter) {
        this.presenter = presenter;
    }
*/
    @Override
    public void stop() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stationsAdapter = new StationsAdapter((ArrayList<StationDataModel>) stationDataModelList);

        stationsAdapter.setStationClicker(new StationClicker() {
            @Override
            public void onStationClicked(String stationId) {
                presenter.startSensorsListActivity(stationId);
            }
        });

        rvStations.setAdapter(stationsAdapter);

    }

    @Override
    public void showStationList(ArrayList<StationDataModel> stationDataModels) {
        stationsAdapter.updateData(stationDataModels);
    }

    @Override
    public void showStartSensorsListActivity(String stationId) {
        Intent intent = new Intent(getActivity(), SensorsListActivity.class);
        intent.putExtra("stationId", stationId);
        startActivity(intent);
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

    class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.StationsViewHolder> {

        ArrayList<StationDataModel> stationDataModels;


        StationClicker stationClicker;

        StationsAdapter(ArrayList<StationDataModel> stationDataModels) {
            this.stationDataModels = stationDataModels;
        }

        class StationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tvStationName;
            TextView tvStationCityName;


            public StationsViewHolder(View itemView) {
                super(itemView);

                tvStationName = (TextView) itemView.findViewById(R.id.tvStationName);
                tvStationCityName = (TextView) itemView.findViewById(R.id.tvStationCityName);

                tvStationName.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                stationClicker.onStationClicked(stationDataModels.get(getAdapterPosition()).getId());
            }
        }

        void setStationDataModels(ArrayList<StationDataModel> stationDataModels) {
            this.stationDataModels = stationDataModels;
        }

        void updateData(ArrayList<StationDataModel> stationDataModels) {
            setStationDataModels(stationDataModels);
            this.notifyDataSetChanged();
        }

        public void setStationClicker(StationClicker stationClicker) {
            this.stationClicker = stationClicker;
        }

        @Override
        public StationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station, parent, false);
            StationsViewHolder stationsViewHolder = new StationsViewHolder(view);
            return stationsViewHolder;
        }

        @Override
        public void onBindViewHolder(StationsViewHolder holder, int position) {
            holder.tvStationCityName.setText(stationDataModels.get(position).getCityName());
            holder.tvStationName.setText(stationDataModels.get(position).getStationName());
        }

        @Override
        public int getItemCount() {
            return stationDataModels.size();
        }

    }

    interface StationClicker {

        void onStationClicked(String id);

    }
}
