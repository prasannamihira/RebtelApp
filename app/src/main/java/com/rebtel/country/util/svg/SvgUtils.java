package com.rebtel.country.util.svg;

import android.content.Context;
import android.widget.ImageView;
import com.pixplicity.sharp.Sharp;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;

public class SvgUtils {
    private static OkHttpClient httpClient;

    /**
     * fetch svg by given web url to target imageview layout
     * @param context
     * @param url
     * @param target
     */
    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream stream = response.body().byteStream();
                Sharp.loadInputStream(stream).into(target);
                stream.close();
            }
        });
    }
}
