package com.mbobiosio.firebaserecycler.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbobiosio.firebaserecycler.R;
import com.squareup.picasso.Picasso;

/**
 * Created by hasan on 3/22/17.
 */

public class MyAdapterClass extends RecyclerView.Adapter<MyAdapterClass.DataListHolder>{

    @Override
    public DataListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DataListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        public void setnewDate(String newDate) {
            TextView data_date = (TextView) mView.findViewById(R.id.publishedDate);
            data_date.setText(newDate);
        }
        public void setQuote(String quote) {
            TextView data_quote = (TextView) mView.findViewById(R.id.quote);
            data_quote.setText(quote);
        }
        public void setContent(String content) {
            TextView data_content = (TextView) mView.findViewById(R.id.content);
            data_content.setText(content);
        }
//        public void setImage(Context context, String image) {
//            ImageView data_image = (ImageView) mView.findViewById(R.id.imageViewy);
//            Picasso.with(context).load(image).into(data_image);
//        }
    }
}
