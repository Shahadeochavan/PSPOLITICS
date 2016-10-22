package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.VotingCenterPojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class VotingCenterAdapter extends BaseAdapter {
    private static ArrayList<VotingCenterPojo> listCenter;

    private LayoutInflater mInflater;

    public VotingCenterAdapter(Context context, ArrayList<VotingCenterPojo> results){
        listCenter = results;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listCenter.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listCenter.get(arg0);
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
            convertView = mInflater.inflate(R.layout.fragment_voting_centers, null);
            holder = new ViewHolder();
            holder.txtplacename = (TextView) convertView.findViewById(R.id.place_name);
            holder.txtaddress = (TextView) convertView.findViewById(R.id.address);
            holder.txtdate = (TextView) convertView.findViewById(R.id.date);
            holder.txtstarttime = (TextView) convertView.findViewById(R.id.start_time);
            holder.txtendtime = (TextView) convertView.findViewById(R.id.end_time);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtplacename.setText(listCenter.get(position).getPlaceName());
        holder.txtaddress.setText(listCenter.get(position).getAddress());
        holder.txtdate.setText(listCenter.get(position).getDate());
        holder.txtstarttime.setText(listCenter.get(position).getStartTime());
        holder.txtendtime.setText(listCenter.get(position).getEndTime());

        return convertView;
    }

    static class ViewHolder{
        TextView txtplacename,txtaddress,txtdate,txtstarttime,txtendtime ;
    }
}
