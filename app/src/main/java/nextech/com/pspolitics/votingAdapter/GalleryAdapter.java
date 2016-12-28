
package nextech.com.pspolitics.votingAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.GalleryPojo;

public class GalleryAdapter extends ArrayAdapter<GalleryPojo> {
    ArrayList<GalleryPojo> galleryPojos;
    //private final ColorMatrixColorFilter grayscaleFilter;
    private Context mContext;
    private int layoutResourceId;
    private LayoutInflater inflater;
    private ArrayList<GalleryPojo> mGridData = new ArrayList<GalleryPojo>();

    public GalleryAdapter(Context mContext, int layoutResourceId, ArrayList<GalleryPojo> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.galleryPojos = galleryPojos;
        System.out.println("I am in download task");
    }


    public void setGridData(ArrayList<GalleryPojo> galleryPojos) {
        this.galleryPojos = galleryPojos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        if (holder.imageView != null) {
            new DownloadImageTask(holder.imageView).execute(galleryPojos.get(position).getImages());
        }
        return row;
    }

    static class ViewHolder {
        ImageView imageView;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage)  {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            System.out.println("I am in download task");
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}