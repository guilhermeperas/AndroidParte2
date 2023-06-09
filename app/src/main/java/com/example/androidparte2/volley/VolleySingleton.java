package com.example.androidparte2.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    private VolleySingleton(Context ctx){
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

    public static VolleySingleton getInstance(Context context) {
        if(mInstance == null)
            return new VolleySingleton(context);
        return mInstance;
    }
    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
