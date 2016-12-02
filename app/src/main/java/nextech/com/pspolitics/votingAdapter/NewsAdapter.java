package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.NewsListPojo;

/**
 * Created by welcome on 10/18/2016.
 */
public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    public NewsAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public NewsAdapter(Context context, List<NewsListPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.newsListPojos=data;
    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView persondate;
        TextView persontime;
        TextView photsInformatin;
        ImageView textShare;
        ImageView personPhoto,newsPhoto;

         NewsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            persondate = (TextView)itemView.findViewById(R.id.news_date);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            newsPhoto = (ImageView)itemView.findViewById(R.id.news_photo);
            persontime = (TextView)itemView.findViewById(R.id.news_time);
            photsInformatin = (TextView)itemView.findViewById(R.id.phots_info);
            textShare = (ImageView)itemView.findViewById(R.id.share);

        }
    }

    List<NewsListPojo> newsListPojos;

    public NewsAdapter(List<NewsListPojo> newsListPojos,Context context){
        this.newsListPojos = newsListPojos;
        this.context=context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_card_news, viewGroup, false);
        NewsViewHolder pvh = new NewsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.personName.setText(newsListPojos.get(i).getName());
        newsViewHolder.persondate.setText(newsListPojos.get(i).getDate());
        newsViewHolder.persontime.setText(newsListPojos.get(i).getTime());
        newsViewHolder.photsInformatin.setText(newsListPojos.get(i).getInformationofphots());
        newsViewHolder.personPhoto.setImageResource(newsListPojos.get(i).getPhotoId());
        newsViewHolder.newsPhoto.setImageResource(newsListPojos.get(i).getPhotsNews());
//        newsViewHolder.textShare.setImageResource(newsListPojos.get(i).share);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer position = (Integer)view.getTag();
                Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), newsListPojos.get(position).getPhotsNews());

                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), mBitmap, "PSPImage", null);
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
               context.startActivity(Intent.createChooser(intent, "Share Image"));

            }
        };

        newsViewHolder.textShare.setOnClickListener(onClickListener);
        newsViewHolder.textShare.setTag(i);

    }

    @Override
    public int getItemCount() {
        return newsListPojos.size();
    }
}