package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Context mContext;
    private List<Note> noteList;
    private DeletingCommunicatior communicatior;


    public NoteAdapter(Context mContext, List<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
        communicatior= (DeletingCommunicatior) mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Note note = noteList.get(position);
        holder.text.setText(note.getText());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                communicatior.deleteOnClick(position, noteList.get(position).getText());

            }
        });


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public ImageButton delete,edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.note_text);
            delete=itemView.findViewById(R.id.deleteNote_btn);
            edit=itemView.findViewById(R.id.editNote_btn);
        }
    }

}
