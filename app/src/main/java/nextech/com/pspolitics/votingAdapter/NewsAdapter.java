package nextech.com.pspolitics.votingAdapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.NewsListPojo;

/**
 * Created by welcome on 10/18/2016.
 */
public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter .ViewHolder> {
    List<NewsListPojo> items;

    public NewsAdapter(String[] names, String[] urls, Bitmap[] images){
        super();
        items = new ArrayList<NewsListPojo>();
        for(int i =0; i<names.length; i++){
            NewsListPojo item = new NewsListPojo();
            item.setName(names[i]);
            item.setUrl(urls[i]);
            item.setImage(images[i]);
            items.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsListPojo list =  items.get(position);
        holder.imageView.setImageBitmap(list.getImage());
        holder.textViewName.setText(list.getName());
        holder.textViewUrl.setText(list.getUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewUrl = (TextView) itemView.findViewById(R.id.textViewUrl);

        }
    }
}