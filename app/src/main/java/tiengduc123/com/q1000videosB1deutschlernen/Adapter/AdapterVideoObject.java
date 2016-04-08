package tiengduc123.com.q1000videosB1deutschlernen.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tiengduc123.com.q1000videosB1deutschlernen.Object.VideoObj;
import tiengduc123.com.q1000videosB1deutschlernen.R;

/**
 * Created by Dell on 12/3/2015.
 */
public class AdapterVideoObject extends ArrayAdapter<VideoObj> {
    public AdapterVideoObject(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public AdapterVideoObject(Context context, int resource, List<VideoObj> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(tiengduc123.com.q1000videosB1deutschlernen.R.layout.content_item_video, null);
        }

        VideoObj p = getItem(position);

        if (p != null) {
            // Anh xa + Gan gia tri
            TextView nameVideo = (TextView) v.findViewById(R.id.txtNameVideo);
            nameVideo.setText(p.nameVideo);

            TextView nameList = (TextView) v.findViewById(R.id.txtNameOfList);
            nameList.setText(p.nameOfList);

            TextView timeVideo = (TextView) v.findViewById(R.id.txtTimeVideo);
            timeVideo.setText(p.timeVideo);

            ImageView img = (ImageView) v.findViewById(R.id.imageView);
            Picasso.with(getContext()).load("http://img.youtube.com/vi/" + p.key + "/0.jpg").into(img);
        }

        return v;
    }
}