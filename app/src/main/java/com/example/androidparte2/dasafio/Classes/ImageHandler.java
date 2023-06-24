package com.example.androidparte2.dasafio.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageHandler {
    private static ImageHandler mInstance = null;
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    private ImageHandler(Context ctx){
        mRequestQueue = Volley.newRequestQueue(ctx);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String,Bitmap>(10); // 10 sera a quantidade de cache que queremos usar
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }
        });
    }

    public static ImageHandler getInstance(Context context) {
        if(mInstance == null)
            return new ImageHandler(context);
        return mInstance;
    }
    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
