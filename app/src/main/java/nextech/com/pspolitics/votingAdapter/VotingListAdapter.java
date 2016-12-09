package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.VotingListPojo;

/**
 * Created by welcome on 10/17/2016.
 */
public class   VotingListAdapter extends  RecyclerView.Adapter<VotingListAdapter.VotingListViewHolder>  {
public VotingListAdapter(Context context) {
        }
private Context context;
private LayoutInflater inflater;


public VotingListAdapter(Context context, List<VotingListPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.votingListPojos=data;
        }

public static class VotingListViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView textfirstName;
    TextView textlastName;
    TextView texmiddleName;
    TextView textWardNumber;
    TextView textPhoneMumber;

    VotingListViewHolder(View itemView) {
        super(itemView);
        cv = (CardView)itemView.findViewById(R.id.cv);
        textfirstName = (TextView)itemView.findViewById(R.id.first_name);
        textlastName = (TextView)itemView.findViewById(R.id.last_name);
        texmiddleName = (TextView)itemView.findViewById(R.id.middle_name);
        textWardNumber = (TextView)itemView.findViewById(R.id.ward_details);
        textPhoneMumber = (TextView)itemView.findViewById(R.id.phone_number);

    }
}

    List<VotingListPojo> votingListPojos;

    public VotingListAdapter(List<VotingListPojo> votingCenterPojos){
        this.votingListPojos = votingListPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public VotingListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_voting_list, viewGroup, false);
        VotingListViewHolder pvh = new VotingListViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(VotingListViewHolder votingCenterViewHolder, int i) {
        votingCenterViewHolder.textfirstName.setText(votingListPojos.get(i).getFirstName());
        votingCenterViewHolder.textlastName.setText(votingListPojos.get(i).getLastName());
        votingCenterViewHolder.texmiddleName.setText(votingListPojos.get(i).getMiddleName());
        votingCenterViewHolder.textWardNumber.setText(votingListPojos.get(i).getWardDetails());
        votingCenterViewHolder.textPhoneMumber.setText(votingListPojos.get(i).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return votingListPojos.size();
    }
}