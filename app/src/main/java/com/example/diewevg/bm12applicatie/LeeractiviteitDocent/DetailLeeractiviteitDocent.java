package com.example.diewevg.bm12applicatie.LeeractiviteitDocent;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diewevg.bm12applicatie.R;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailLeeractiviteitDocent extends Fragment {
    public Button reviewButtonDocent;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent.OnFragmentInteractionListener mListener;

    public DetailLeeractiviteitDocent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailLeeractiviteit.
     */
    public static com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent newInstance(String param1, String param2) {
        com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent fragment = new com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent();
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
        View RootView = inflater.inflate(R.layout.fragment_detail_leeractiviteit_docent, container, false);
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        Bundle bundle = this.getArguments();
        final String leeractiviteitId = bundle.getString("LeeractiviteitId");
        setData(leeractiviteitId, RootView);
        final String ip = getString(R.string.ipconfig);

        reviewButtonDocent = (Button) RootView.findViewById(R.id.reviewButtonDocent);
        reviewButtonDocent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View RootView)
            {
                Log.d("review click", "review");
                Fragment feedback = new Feedback();
                Bundle bundle = new Bundle();
                bundle.putString("LeeractiviteitId", leeractiviteitId);
                feedback.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, feedback).commit();
            }
        });

        String text =  "";
        if (leeractiviteitId.equals("0")) {
            text = readFile("file" + leeractiviteitId, getActivity());
        }
        if (leeractiviteitId.equals("1")){
            text = readFile("file1", getActivity());
        }

        //Find the view by its id
        TextView tv = (TextView) RootView.findViewById(R.id.review);

        //Set the text
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
        if (context instanceof com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent.OnFragmentInteractionListener) {
            mListener = (com.example.diewevg.bm12applicatie.LeeractiviteitDocent.DetailLeeractiviteitDocent.OnFragmentInteractionListener) context;
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

    public String readFile(String sFileName, Context context) {
        try {
            File file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "MyFile");
            file.mkdirs();
            File gpxfile = new File(file, sFileName);

            if (!gpxfile.exists()) {
                return "Nog geen feedback toegevoegd";
            }

            StringBuilder text = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(gpxfile));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Er is iets fout gegaan bij opslaan van de review";
    }

    public void setData(String id, View view) {
        //Find the view by its id
        final TextView naam = (TextView) view.findViewById(R.id.naamLeeractiviteitDocent);
        final TextView soort = (TextView) view.findViewById(R.id.soortLeeractiviteitDocent);
        final TextView datum = (TextView) view.findViewById(R.id.datumLeeractiviteitDocent);
        final TextView tijdstip = (TextView) view.findViewById(R.id.tijdstipLeeractiviteitDocent);
        final TextView luchtvochtigheid = (TextView) view.findViewById(R.id.luchtvochtigheidLeeractiviteitDocent);
        final TextView temperatuur = (TextView) view.findViewById(R.id.temperatuurDocent);
        String ip = getString(R.string.ipconfig);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://"+ ip + "/api/activities/" + id;
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        SimpleDateFormat datumFormat = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm");

                        JSONObject cursus = response.optJSONObject("Course");

                        String cursusCode = cursus.optString("CourseCode");
                        String soortCollege = response.optString("ActivityType");
                        String datumCollege = response.optString("DateTime");
                        String startTijd = response.optString("StartTime");
                        String eindTijd = response.optString("EndTime");

                        naam.setText("Naam: " + cursusCode);
                        soort.setText("Soort college: " + soortCollege);
                        datum.setText("Datum: " + datumCollege);
                        tijdstip.setText("Tijdstip: " + startTijd + "-" + eindTijd);
                        luchtvochtigheid.setText("Luchtvochtigheid: 5%");
                        temperatuur.setText("Graden Celsius: 23");
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error array", error.toString());
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }
}
