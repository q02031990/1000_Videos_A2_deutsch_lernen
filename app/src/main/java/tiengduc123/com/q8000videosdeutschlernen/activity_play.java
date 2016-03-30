package tiengduc123.com.q8000videosdeutschlernen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import tiengduc123.com.q8000videosdeutschlernen.Database.DatabaseHelper;
import tiengduc123.com.q8000videosdeutschlernen.Adapter.AdapterVideoObject;
import tiengduc123.com.q8000videosdeutschlernen.Object.VideoObj;

public class activity_play extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    YouTubePlayer _YouTubePlayer;
    YouTubePlayerView youTubePlayerView;
    ArrayList<VideoObj> mang;
    DatabaseHelper db;
    ListView lv;

    public static final String API_KEY = "AIzaSyCPc8rPMgkXlmvQrI0VXL5x3IHl4s6iai8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_play);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplayerview);
        youTubePlayerView.setAlpha(1.0f);
        youTubePlayerView.initialize(API_KEY, this);
        NapDuLieuLenListView();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        _YouTubePlayer = youTubePlayer;
        if (!b) {
            Bundle bu = getIntent().getExtras();
            String VideoID = bu.getString("key");
            _YouTubePlayer.loadVideo(VideoID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public void playVideo(String key){
        _YouTubePlayer.loadVideo(key);
    }

    public void NapDuLieuLenListView(){
        Bundle bu = getIntent().getExtras();
        String ID = bu.getString("ListID");

        db = new DatabaseHelper(this);
        AdapterVideoObject adapter;
        ArrayList<VideoObj> _Cursor;
        _Cursor = db.GetVideoObjByList(ID);
        mang = new ArrayList<VideoObj>();
        lv = (ListView) findViewById(R.id.listView);

        try {
            for (int i = 0; i < _Cursor.size(); i++) {
                String title = _Cursor.get(i).nameVideo;
                if(title.length()>30){
                    title =title.substring(0,30)+ "...";
                }
                mang.add(new VideoObj(
                        title,
                        _Cursor.get(i).timeVideo,
                        _Cursor.get(i).key,
                        "WWW.TiengDuc123.com"
                ));
            }

            //adapter.clear();
            adapter = new AdapterVideoObject(
                    getApplicationContext(),
                    R.layout.activity_item_video,
                    mang
            );



            lv.setAdapter(adapter);


            //load lai list
            adapter.notifyDataSetChanged();
            lv.invalidateViews();
            lv.refreshDrawableState();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    playVideo(mang.get(position).key);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        if (mang.size() > 1) {
            Toast.makeText(getApplicationContext(), mang.size() + " Videos were loaded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please wait or Try to open www.TiengDuc123.com!", Toast.LENGTH_LONG).show();
        }
    }
}

