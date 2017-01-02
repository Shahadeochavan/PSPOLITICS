
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
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.GalleryPojo;

public class GalleryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private LayoutInflater inflater;
    public GalleryAdapter(Context context,List<GalleryPojo> results){
        this.context=context;
        //inflater= LayoutInflater.from(context);
        this.galleryPojos=results;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return galleryPojos.size();

    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return galleryPojos.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    List<GalleryPojo> galleryPojos;

    public GalleryAdapter(List<GalleryPojo> galleryPojos){
        this.galleryPojos = galleryPojos;
        System.out.println("Respsone is : ");
    }
    public View getView(int position, View itemView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(itemView == null){
            System.out.println("Respsone is : ");

            LayoutInflater lInflater = (LayoutInflater)context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.gallery_list, parent, false);
            holder = new ViewHolder();
            holder.imgGallery = (ImageView) itemView.findViewById(R.id.grid_item_image);

            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }
        new DownloadImageTask(holder.imgGallery).execute(galleryPojos.get(position).getImages());

        return itemView;
    }

    static class ViewHolder{
        ImageView imgGallery;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];

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
