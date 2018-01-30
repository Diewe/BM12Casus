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

import com.example.diewevg.bm12applicatie.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

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


        /*String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;
        File file = new File(getActivity().getFilesDir(), filename);

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_detail_leeractiviteit, container, false);

        ratingBar = (RatingBar) RootView.findViewById(R.id.ratingBar);
        reviewButton = (Button) RootView.findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View RootView)
            {
                entryData = (EditText) getActivity().findViewById(R.id.textInput);
                String dataToSave = entryData.getText().toString();
                String FILENAME = "myFile";
                generateNoteOnSD(getActivity(), FILENAME, dataToSave);
                //saveInInternalStorage(RootView, getActivity());
            }
        });

        String text = readFile("myFile", getActivity());

        //Find the view by its id
        TextView tv = (TextView) RootView.findViewById(R.id.review);

        //Set the text
        tv.setText(text);

        //File sdcard = Environment.getExternalStorageDirectory();
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
            File file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), sFileName);

            File gpxfile = new File(file, sFileName);


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
            TextView tv = (TextView) getActivity().findViewById(R.id.review);

            //Set the text
            tv.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String sFileName, Context context) {
        try {
            File file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), sFileName);

            File gpxfile = new File(file, sFileName);

            if (!gpxfile.exists()) {
                return "Nog geen feedback toegevoegd";
            }

            File sdcard = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

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
}
