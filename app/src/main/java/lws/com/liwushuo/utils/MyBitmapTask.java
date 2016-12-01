package lws.com.liwushuo.utils;

/**
 * Created by zhangziyang on 2016/10/14.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static lws.com.liwushuo.app.OurApp.context;

public class MyBitmapTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView bannerImage;
    private OnBitMapLoadCompleteListener onBitMapLoadCompleteListener;

    public MyBitmapTask(Context context, ImageView bannerImage) {
//        this.onBitMapLoadCompleteListener = onBitMapLoadCompleteListener;
        this.bannerImage = bannerImage;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        RenderScript renderScript = RenderScript.create(context);
        Element e = Element.RGBA_8888(renderScript);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, e);
        Allocation in = Allocation.createFromBitmap(renderScript, bitmap);
        blur.setRadius(20);
        Bitmap out = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        blur.setInput(in);
        Allocation aout = Allocation.createFromBitmap(renderScript, out);
        blur.forEach(aout);
        aout.copyTo(out);
        bannerImage.setImageBitmap(out);

    }

    public interface OnBitMapLoadCompleteListener{
        void onBitMapLoadComplete(Bitmap bitmap);
    }
}
