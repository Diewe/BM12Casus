package com.example.diewevg.bm12applicatie.LeeractiviteitDocent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.diewevg.bm12applicatie.Leeractiviteit.LeeractiviteitAdapter;
import com.example.diewevg.bm12applicatie.Models.Activity;
import com.example.diewevg.bm12applicatie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeeractiviteitDocent extends Fragment {
    public ListView listView;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent.OnFragmentInteractionListener mListener;

    public LeeractiviteitDocent() {
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
    public static com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent newInstance(String param1, String param2) {
        com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent fragment = new com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent();
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
        View RootView = inflater.inflate(R.layout.fragment_leeractiviteit_docent, container, false);

        // Construct the data source
        ArrayList<Activity> arrayOfLeeractiviteit = new ArrayList<Activity>();
        final LeeractiviteitAdapter adapter = new LeeractiviteitAdapter(getActivity(), arrayOfLeeractiviteit);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String ip = getString(R.string.ipconfig);
        String url ="http://" + ip + "/api/activities";
        final List<Activity> leerActiviteiten = new ArrayList<Activity>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject studentJson = response.getJSONObject(i);

                                // Get the current student (json object) data
                                Integer id = studentJson.getInt("Id");
                                JSONObject course = studentJson.getJSONObject("Course");
                                String courseCode = course.optString("CourseCode");
                                String soortCollege = studentJson.getString("ActivityType");
                                String startTijd = studentJson.getString("StartTime");
                                String eindTijd = studentJson.getString("EndTime");
                                String datum = studentJson.getString("DateTime");

                                Activity leerActiviteit = new Activity(id, soortCollege, datum, startTijd, eindTijd, courseCode);
                                leerActiviteiten.add(leerActiviteit);
                                adapter.add(leerActiviteit);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("Error array", error.toString());
                    }
                }
        );
        queue.add(jsonArrayRequest);

        // Attach the adapter to a ListView
        listView = (ListView) RootView.findViewById(R.id.LijstLeeractiviteitDocent);
        listView.setAdapter(adapter);

        // Item Click Listener for the listview
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                Fragment fragment = new DetailLeeractiviteitDocent();
                String selectedId = ((Activity) listView.getAdapter().getItem(position)).getId().toString();

                Bundle bundle = new Bundle();
                bundle.putString("LeeractiviteitId", selectedId);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();

            }
        };

        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);

        // Inflate the layout for this fragment
        return RootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent.OnFragmentInteractionListener) {
            mListener = (com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocent.OnFragmentInteractionListener) context;
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
