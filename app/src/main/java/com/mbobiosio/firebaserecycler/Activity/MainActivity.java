package com.mbobiosio.firebaserecycler.Activity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mbobiosio.firebaserecycler.R;
import com.mbobiosio.firebaserecycler.model.ModelClass;

import cn.hiroz.uninstallfeedback.FeedbackUtils;
import net.cachapa.expandablelayout.ExpandableLayout;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    private static final int UNSELECTED = -1;
    private static int selectedItem = UNSELECTED;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        /*Recycler View */

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
            protected void populateViewHolder(final DataListHolder viewHolder, ModelClass model, final int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setnewDate(model.getNewDate());
                viewHolder.setQuote(model.getQuote());
              //  viewHolder.setImage(MainActivity.this, model.getImage());
                viewHolder.setContent(model.getContent());
                viewHolder.itemPosition = position;

                viewHolder.cardView.setSelected(false);
                viewHolder.expandableLayout.collapse(false);


                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataListHolder dataListHolder = (DataListHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);

                        if (dataListHolder != null) {
                            dataListHolder.cardView.setSelected(false);
                            dataListHolder.expandableLayout.collapse();
                        }

                        if (viewHolder.itemPosition == selectedItem) {
                            selectedItem = UNSELECTED;
                        } else {
                            viewHolder.cardView.setSelected(true);
                            viewHolder.expandableLayout.expand();
                            selectedItem = position;
                        }
                    }
                });

            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class DataListHolder extends RecyclerView.ViewHolder {

        private ExpandableLayout expandableLayout;
        private TextView data_content;
//        private TextView contentTextView;
        private CardView cardView;
        private int itemPosition;
        View mView;

        public DataListHolder(View itemView) {
            super(itemView);
            mView = itemView;
            cardView = (CardView) mView.findViewById(R.id.recyclerHeader);

            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
            expandableLayout.setInterpolator(new OvershootInterpolator());
            data_content = (TextView) mView.findViewById(R.id.content);
//            contentTextView = (TextView) itemView.findViewById(R.id.content);

        }
        public void setTitle(String title) {
            TextView data_title = (TextView) mView.findViewById(R.id.titleText);
            data_title.setText(title);
        }
        public void setContent(String content) {
            data_content.setText(content);
        }

        public void setQuote(String quote) {
            TextView data_quote = (TextView) mView.findViewById(R.id.quote);
            data_quote.setText(quote);
        }
        public void setnewDate(String newDate) {
            TextView data_date = (TextView) mView.findViewById(R.id.publishedDate);
            data_date.setText(newDate);
        }

//        public void setImage(Context context, String image) {
//            ImageView data_image = (ImageView) mView.findViewById(R.id.imageViewy);
//            Picasso.with(context).load(image).into(data_image);
//        }

    }


    public class Firebase extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
