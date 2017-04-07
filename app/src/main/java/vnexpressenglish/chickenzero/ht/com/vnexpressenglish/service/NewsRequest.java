package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.service;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.MainActivity;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.models.NewsObject;

/**
 * Created by QuyDV on 4/7/17.
 */

public class NewsRequest extends AsyncTaskLoader<NewsObject> {
    private MainActivity mContext;
    private String mUrl;

    public NewsRequest(MainActivity context,String url) {
        super(context);
        this.mContext = context;
        mUrl = url;
    }

    @Override
    public void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
            mContext.showLoading();
        }
    }

    @Override
    public NewsObject loadInBackground() {
        Document doc = null;
        try {
            doc = Jsoup.connect(mUrl).data("query", "Java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(10000).post();
            Element es = doc.getElementsByClass("width_common folder_item_list").first();

            Elements datas = es.getElementsByClass("block_image_news width_common");

            for (Element element : datas) {
                Element e = element.getElementsByClass("thumb").first();
                String title = e.getElementsByAttribute("title").attr("title");
                String image = e.getElementsByAttribute("src").attr("src");
                String link = e.getElementsByAttribute("href").attr("href");
            }

            Log.i("Data", String.valueOf(datas.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }

}
