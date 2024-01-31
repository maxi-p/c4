package com.zybooks.connectfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GameOptionsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_options, container, false);
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        int num = sharedPref.getInt("level", 0);

        int radioId = R.id.radio_easy;
        if (num == 0) {
            radioId = R.id.radio_easy;
        }
        else if (num == 1) {
            radioId = R.id.radio_medium;
        }
        else if (num == 2) {
            radioId = R.id.radio_hard;
        }
        RadioButton radio = rootView.findViewById(radioId);
        radio.setChecked(true);

        // Add click callback to all radio buttons
        RadioGroup colorRadioGroup = rootView.findViewById(R.id.radiogr);
        for (int i = 0; i < colorRadioGroup.getChildCount(); i++) {
            radio = (RadioButton) colorRadioGroup.getChildAt(i);
            radio.setOnClickListener(this::onLevelSelected);
        }

        return rootView;
    }

    private void onLevelSelected(View view) {
        int num = 0;
        if (view.getId() == R.id.radio_easy) {
            num = 0;
        } else if (view.getId() == R.id.radio_medium) {
            num = 1;
        } else if (view.getId() == R.id.radio_hard) {
            num = 2;
        }

        // Save selected color ID in SharedPreferences
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("level", num);
        editor.apply();
    }
}