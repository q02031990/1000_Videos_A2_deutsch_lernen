package tiengduc123.com.q8000videosdeutschlernen.QFile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Dell on 24/03/2016.
 */
public class CheckVersion extends AsyncTask<String, String, String> {

    Context context;
    int Version;
    private String URL_FOR_NEW_VERSION = "http://tiengduc123.com/app/DeutscheFilm/CheckVersion.php?p=";

    public CheckVersion(Context context, int Version){
        this.context = context;
        this.Version = Version;
    }
    @Override
    protected String doInBackground(String... params) {
        qInternet _qInternet = new qInternet();
        Random b = new Random();
        int i = b.nextInt(9999999);
        String url = "http://tiengduc123.com/app/DeutscheFilm/CheckVersion.php?CheckVersion=" + i;
        String temp = _qInternet.docNoiDung_Tu_URL(url);
        return temp.replaceAll("(\\r|\\n)", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (Integer.parseInt(s) > Version) {
            Toast.makeText(context, "Wunderbar, wir haben neue Application", Toast.LENGTH_LONG).show();
            // redirect for download new version
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_FOR_NEW_VERSION));
            context.startActivity(browserIntent);
        }else{
            //Toast.makeText(context, "Your app is newest!!!", Toast.LENGTH_LONG).show();
        }
    }
}
