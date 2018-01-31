package com.example.diewevg.bm12applicatie.Leeractiviteit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.diewevg.bm12applicatie.R;
import com.example.diewevg.bm12applicatie.Resultaten.DetailResultaat;
import com.example.diewevg.bm12applicatie.Resultaten.ResultaatAdapter;
import com.example.diewevg.bm12applicatie.Resultaten.ResultaatModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Leeractiviteit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Leeractiviteit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Leeractiviteit extends Fragment {
    public ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Leeractiviteit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Leeractiviteit.
     */
    // TODO: Rename and change types and number of parameters
    public static Leeractiviteit newInstance(String param1, String param2) {
        Leeractiviteit fragment = new Leeractiviteit();
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
        View RootView = inflater.inflate(R.layout.fragment_leeractiviteit, container, false);

        // Construct the data source
        ArrayList<LeeractiviteitModel> arrayOfLeeractiviteit = new ArrayList<LeeractiviteitModel>();
        // Create the adapter to convert the array to views
        LeeractiviteitAdapter adapter = new LeeractiviteitAdapter(getActivity(), arrayOfLeeractiviteit);

        LeeractiviteitModel newLeeractiviteit = new LeeractiviteitModel("Werkcollege", "BM01");
        adapter.add(newLeeractiviteit);

        LeeractiviteitModel extraLeeractiviteit = new LeeractiviteitModel("Hoorcollege", "BM12");
        adapter.add(extraLeeractiviteit);
        // Attach the adapter to a ListView
        listView = (ListView) RootView.findViewById(R.id.LijstLeeractiviteit);
        listView.setAdapter(adapter);

        // Item Click Listener for the listview
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                Fragment fragment = null;
                fragment = new DetailLeeractiviteit();

                String positie = String.valueOf(position);
                Bundle bundle = new Bundle();
                bundle.putString("Leeractiviteit", positie);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();

            }
        };

        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);
        //new BackGround().execute();
        // Inflate the layout for this fragment
        return RootView;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
