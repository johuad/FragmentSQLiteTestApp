package com.learningapp.learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyFragmentViewAdapter extends RecyclerView.Adapter<MyFragmentViewAdapter.ViewHolder> {

    // List of people to populate the RecyclerView with.
    private static ArrayList<Person> people;

    MyFragmentViewAdapter(ArrayList<Person> i) {
        people = i;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        // Inflate RecyclerView with cards.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyFragmentViewAdapter.ViewHolder holder, int position) {
        // Create Person object for current card.
        final Person currentPerson = people.get(position);
        // Set Person name to name TextView.
        holder.nameField.setText(currentPerson.getName());
        holder.ageField.setText(String.valueOf(currentPerson.getAge()));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    /* Member class for ViewHolder */
    protected class ViewHolder extends RecyclerView.ViewHolder {
        protected Context context;
        protected TextView nameField;
        protected TextView ageField;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            // Initialize context
            context = itemView.getContext();

            // Grab CardView TextView IDs.
            nameField = itemView.findViewById(R.id.personName);
            ageField = itemView.findViewById(R.id.personAge);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

            itemView.setOnClickListener(view -> {
                // Create person from card clicked by user.
                final Person thisPerson = people.get(getAdapterPosition());
                // Try to delete the person & remove the card from the RecyclerView.
                try {
                    dataBaseHelper.deleteOne(thisPerson);
                }
                catch(Exception e) {
                    Toast.makeText(context, "Deletion Failure.", Toast.LENGTH_SHORT).show();
                }
                people.remove(thisPerson);
                notifyItemRemoved(getAdapterPosition());
            });

        }
    }
}
