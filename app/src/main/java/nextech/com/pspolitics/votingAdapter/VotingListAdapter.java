package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.VotingListPojo;

/**
 * Created by welcome on 10/17/2016.
 */
public class VotingListAdapter extends BaseAdapter {
    private static ArrayList<VotingListPojo> listContact;

    private LayoutInflater mInflater;

    public VotingListAdapter(Context context, ArrayList<VotingListPojo> results){
        listContact = results;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listContact.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listContact.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.fragment_voting_list, null);
            holder = new ViewHolder();
            holder.txtlastName= (TextView)convertView.findViewById(R.id.last_name);
            holder.txtfirstName= (TextView)convertView.findViewById(R.id.first_name);
            holder.txtmiddleName= (TextView)convertView.findViewById(R.id.middle_name);
            holder.txtphoneNumber= (TextView)convertView.findViewById(R.id.phone_number);
            holder.txtwardDetails= (TextView)convertView.findViewById(R.id.ward_details);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtlastName.setText(listContact.get(position).getLastName());
        holder.txtfirstName.setText(listContact.get(position).getFirstName());
        holder.txtmiddleName.setText(listContact.get(position).getMiddleName());
        holder.txtphoneNumber.setText(listContact.get(position).getPhoneNumber());
        holder.txtwardDetails.setText(listContact.get(position).getWardDetails());

        return convertView;
    }

    static class ViewHolder{
        TextView txtfirstName,txtlastName,txtmiddleName,txtphoneNumber,txtwardDetails;
    }
}
