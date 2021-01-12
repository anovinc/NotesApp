package com.example.notes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogNewNote extends DialogFragment implements View.OnClickListener {
    private TextView Title,Cancel,Enter;
    private EditText newNote;
    private  Communicator communicator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        communicator= (Communicator) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_new_note,null);
        Title=view.findViewById(R.id.newNoteDialog_Tv);
        Cancel=view.findViewById(R.id.NO__newNote);
        newNote=view.findViewById(R.id.newNote_ET);
        Enter=view.findViewById(R.id.accept_newNote);
        Enter.setOnClickListener(this);
        Cancel.setOnClickListener(this);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.accept_newNote){
            communicator.onDialogMessage(newNote.getText().toString());
            dismiss();

        }

        if(view.getId()==R.id.NO__newNote){
            dismiss();
        }
    }
}
