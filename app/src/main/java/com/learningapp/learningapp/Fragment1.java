package com.learningapp.learningapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    private EditText nameText;
    private EditText ageText;

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1, container, false);
        // Initialize text fields
        nameText = view.findViewById(R.id.name);
        ageText = view.findViewById(R.id.age);
        // Initialize buttons
        Button commitButton, viewButton;
        commitButton = view.findViewById(R.id.commit);
        viewButton = view.findViewById(R.id.viewData);
        // Check if name/age fields are empty, toast warning if so, create entry if not.
        commitButton.setOnClickListener(view -> {
            if(nameText.getText().toString().isEmpty() || ageText.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Cannot leave name/age blank.",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                createEntry();
            }
        });
        // Go to Fragment 2 to view data.
        viewButton.setOnClickListener(view -> {
            // Go to Fragment 2 to view data.
            Fragment fragment = new Fragment2();
            replaceFragment(fragment);
        });

        return view;
    }

    /* Creates a person object and commits its members to the database. */
    private void createEntry()
    {
        Person person;

        try {
            person = new Person(-1, nameText.getText().toString(),
                    Integer.parseInt(ageText.getText().toString()));
        }
        catch (Exception e) {
            person = new Person(-1, "error", 0);
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        boolean success = dataBaseHelper.addOne(person);

        Toast.makeText(getContext(), "Success = " + success, Toast.LENGTH_SHORT).show();
    }

    /* Replaces current fragment in parent with a new fragment. */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        // Add to the backstack so we can return to current fragment.
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}