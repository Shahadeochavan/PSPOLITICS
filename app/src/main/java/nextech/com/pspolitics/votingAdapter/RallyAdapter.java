package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.RallyPojo;

/**
 * Created by welcome on 10/21/2016.
 */
public class RallyAdapter extends BaseAdapter {
    private static ArrayList<RallyPojo> rallyList;

    private LayoutInflater mInflater;

    public RallyAdapter(Context context, ArrayList<RallyPojo> results){
        rallyList = results;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return rallyList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return rallyList.get(arg0);
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
            convertView = mInflater.inflate(R.layout.fragment_rally, null);
            holder = new ViewHolder();
            holder.txtstartPlace= (TextView)convertView.findViewById(R.id.start_place_name);
            holder.txtendPlace= (TextView)convertView.findViewById(R.id.end_place_name);
            holder.txtdate= (TextView)convertView.findViewById(R.id.date);
            holder.txtstarttime= (TextView)convertView.findViewById(R.id.start_time);
            holder.txtendtime= (TextView)convertView.findViewById(R.id.end_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtstartPlace.setText(rallyList.get(position).getStartPlaceName());
        holder.txtendPlace.setText(rallyList.get(position).getEndPlaceName());
        holder.txtdate.setText(rallyList.get(position).getDate());
        holder.txtstarttime.setText(rallyList.get(position).getStartTime());
        holder.txtendtime.setText(rallyList.get(position).getEndTime());

        return convertView;
    }

    static class ViewHolder{
        TextView txtstartPlace,txtendPlace,txtdate,txtstarttime,txtendtime;
    }
}

