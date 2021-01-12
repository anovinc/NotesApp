package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements Communicator,DeletingCommunicatior {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;
    private ImageButton buttonAdd;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyler_view);
        noteList=new ArrayList<>();
        noteAdapter= new NoteAdapter(this,noteList);
        buttonAdd=findViewById(R.id.add_button);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteAdapter);
        realm=Realm.getDefaultInstance();
        setNotes();
    }

    private void setNotes(){
        RealmResults<Note> results=realm.where(Note.class).findAll();
        for(Note note: results)
        noteList.add(note);
        noteAdapter.notifyDataSetChanged();
    }

    private void showDialog(){
        FragmentManager manager=getSupportFragmentManager();
        MyDialogNewNote myDialogNewNote=new MyDialogNewNote();
        myDialogNewNote.show(manager,"MyDialog");
    }

    @Override
    public void onDialogMessage(String message) {
        realm.beginTransaction();
        Note note=realm.createObject(Note.class);
        note.setText(message);
        realm.commitTransaction();
        noteList.add(note);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteOnClick(int position,String text) {
        RealmResults<Note> result=realm.where(Note.class).equalTo("Text",text).findAll();
        realm.beginTransaction();
        result.deleteFirstFromRealm();
        realm.commitTransaction();
        noteList.remove(position);
        noteAdapter.notifyItemRemoved(position);
        noteAdapter.notifyItemRangeChanged(position,noteList.size());
    }
}
