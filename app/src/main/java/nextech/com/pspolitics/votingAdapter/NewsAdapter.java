package nextech.com.pspolitics.votingAdapter;

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

    List<NewsListPojo> persons;

    public NewsAdapter(List<NewsListPojo> persons){
        this.persons = persons;
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
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.persondate.setText(persons.get(i).date);
        personViewHolder.persontime.setText(persons.get(i).time);
        personViewHolder.photsInformatin.setText(persons.get(i).informationofphots);
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        personViewHolder.newsPhoto.setImageResource(persons.get(i).photsNews);
        personViewHolder.textShare.setImageResource(persons.get(i).share);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}