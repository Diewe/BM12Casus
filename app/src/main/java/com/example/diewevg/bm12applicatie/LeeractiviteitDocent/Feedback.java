package com.example.diewevg.bm12applicatie.LeeractiviteitDocent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.diewevg.bm12applicatie.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Feedback.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Feedback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Feedback extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public Button reviewButton;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Feedback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Feedback.
     */
    public static Feedback newInstance(String param1, String param2) {
        Feedback fragment = new Feedback();
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
        View RootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        String text = "Geen feedback gevonden";
        TextView feedbackLijst = RootView.findViewById(R.id.feedbackLijst);
        Bundle bundle = this.getArguments();
        final String leeractiviteitId = bundle.getString("LeeractiviteitId");

        text = readFile("file" + leeractiviteitId, getActivity());

        if (!text.equals("Geen feedback gevonden")){
            String[] splitData = text.split("&&");
            String review = splitData[0];
            String stars = splitData[1];

            //Set the text
            text = "Feedback: " + splitData[0] + " Rating: " + splitData[1];
        }

        feedbackLijst.setText(text);

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
}
