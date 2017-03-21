package com.mbobiosio.firebaserecycler;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mbobiosio.firebaserecycler.model.ModelClass;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dataList;
    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        /*Recycler View */

        dataList = (RecyclerView) findViewById(R.id.list);
        dataList.setHasFixedSize(true);
        dataList.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Data");
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ModelClass, DataListHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelClass, DataListHolder>(
                ModelClass.class,
                R.layout.activity_card,
                DataListHolder.class,
                dbRef) {
            @Override
            protected void populateViewHolder(DataListHolder viewHolder, ModelClass model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(MainActivity.this, model.getImage());
                viewHolder.setContent(model.getContent());
            }
        };
        dataList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class DataListHolder extends RecyclerView.ViewHolder {
        View mView;

        public DataListHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title) {
            TextView data_title = (TextView) mView.findViewById(R.id.titleText);
            data_title.setText(title);
        }
        public void setContent(String content) {
            TextView data_content = (TextView) mView.findViewById(R.id.content);
            data_content.setText(content);
        }
        public void setImage(Context context, String image) {
            ImageView data_image = (ImageView) mView.findViewById(R.id.imageViewy);
            Picasso.with(context).load(image).into(data_image);
        }
    }
    public class Firebase extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
