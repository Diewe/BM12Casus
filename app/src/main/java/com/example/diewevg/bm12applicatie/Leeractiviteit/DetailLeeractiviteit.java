package com.example.diewevg.bm12applicatie.Leeractiviteit;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diewevg.bm12applicatie.Models.Activity;
import com.example.diewevg.bm12applicatie.Models.Teacher;
import com.example.diewevg.bm12applicatie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailLeeractiviteit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailLeeractiviteit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailLeeractiviteit extends Fragment {
    public RatingBar ratingBar;
    public EditText entryData;
    public Button reviewButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailLeeractiviteit() {
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
    // TODO: Rename and change types and number of parameters
    public static DetailLeeractiviteit newInstance(String param1, String param2) {
        DetailLeeractiviteit fragment = new DetailLeeractiviteit();
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
        View RootView = inflater.inflate(R.layout.fragment_detail_leeractiviteit, container, false);
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        Bundle bundle = this.getArguments();
        final String leeractiviteitId = bundle.getString("LeeractiviteitId");
        setData(leeractiviteitId, RootView);
        final String ip = getString(R.string.ipconfig);

        ratingBar = (RatingBar) RootView.findViewById(R.id.ratingBar);
        reviewButton = (Button) RootView.findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View RootView)
            {
                entryData = (EditText) getActivity().findViewById(R.id.textInput);
                Float stars = ratingBar.getRating();
                String starsString = Float.toString(stars);
                String dataToSave = entryData.getText().toString();
                String dataStars = dataToSave + "&&" + starsString;
                String FILENAME = "myFile";
                Log.d("idd", leeractiviteitId);
                if (leeractiviteitId.equals("1")) {
                    FILENAME = "file0";
                }
                if (leeractiviteitId.equals("2")){
                    FILENAME = "file1";
                }
                Log.d("filename", FILENAME);
                generateNoteOnSD(getActivity(), FILENAME, dataStars);
                //String url ="http://"+ ip + "/api/feedbacks/";

                //Map<String, String> params = new HashMap();
                //params.put("FirstName", starsString);
               // params.put("LastName", entryData.getText().toString());
                //params.put("Email", "test@mail.com");
                //params.put("Activity", "Id:1");

                /*Map<String, String> params = new HashMap();
                params.put("Rating", starsString);
                params.put("Note", entryData.getText().toString());
                params.put("Activity", "1");
                params.put("Student", "Id:1");
                params.put("Teacher", "{Id:1}");*/

                //Map<String, Integer> params = new HashMap();
                //params.put("Activity", 1);


                //JSONObject parameters = new JSONObject(params);

                /*JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("post error", error.toString());
                        error.printStackTrace();
                    }
                });*/

                //queue.add(jsonRequest);

            }
        });


        //Find the view by its id
        TextView tv = (TextView) RootView.findViewById(R.id.review);
        String text =  "";
        if (leeractiviteitId.equals("1")) {
            text = readFile("file0", getActivity());
        }
        if (leeractiviteitId.equals("2")){
            text = readFile("file1", getActivity());
        }

        if (!text.equals("")){
            String[] splitData = text.split("&&");
            String review = splitData[0];
            String stars = splitData[1];
            float floatStars = Float.parseFloat(stars);

            //Set the text
            tv.setText(review);
            ratingBar.setRating(floatStars);
        }

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

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            //sFileName = "myFile";
            File file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "myFile");

            File gpxfile = new File(file, sFileName);
            gpxfile.createNewFile();

            if (!gpxfile.exists()) {
                gpxfile.createNewFile();
            }

            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();

            String text = readFile(sFileName, context);

            //Find the view by its id
            String[] splitData = text.split("&&");
            String review = splitData[0];
            String stars = splitData[1];
            float floatStars = Float.parseFloat(stars);

            TextView tv = (TextView) getActivity().findViewById(R.id.review);

            //Set the text
            tv.setText(review);
            ratingBar.setRating(floatStars);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        final TextView naam = (TextView) view.findViewById(R.id.naamLeeractiviteit);
        final TextView soort = (TextView) view.findViewById(R.id.soortLeeractiviteit);
        final TextView docent = (TextView) view.findViewById(R.id.docentLeeractiviteit);
        final TextView datum = (TextView) view.findViewById(R.id.datumLeeractiviteit);
        final TextView tijdstip = (TextView) view.findViewById(R.id.tijdstipLeeractiviteit);
        final TextView luchtvochtigheid = (TextView) view.findViewById(R.id.luchtvochtigheidLeeractiviteit);
        final TextView temperatuur = (TextView) view.findViewById(R.id.temperatuur);
        String ip = getString(R.string.ipconfig);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://"+ ip + "/api/activities/" + id; //http://ptsv2.com/t/bm12/post
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        SimpleDateFormat datumFormat = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm");

                        JSONObject cursus = response.optJSONObject("Course");
                        docent.setText("Docent: ");
                        try
                        {
                            JSONArray docenten = response.getJSONArray("Teachers");

                            for(int i=0;i<docenten.length();i++)
                            {
                                JSONObject docentObject = docenten.getJSONObject(i);
                                String voornaamDocent = docentObject.optString("FirstName");
                                String achternaamDocent = docentObject.optString("LastName");
                                String emailDocent = docentObject.optString("Email");

                                if (i==0){
                                    docent.append(voornaamDocent + " " + achternaamDocent);
                                } else {
                                    docent.append(" + " + voornaamDocent + " " + achternaamDocent);
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

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
                        // Do something when error occurred
                        Log.d("Error array", error.toString());
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }
}
