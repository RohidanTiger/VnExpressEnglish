package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.view.fragments;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.BaseFragment;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.R;

/**
 * Created by QuyDV on 4/5/17.
 */

public class PhotosFragment extends BaseFragment {
    @Override
    protected int getViewContent() {
        return R.layout.photos_fragment;
    }

    @Override
    protected void initUI() {
        new RetrieveFeedTask().execute("http://vnexpress.net/hoc-tu-vung-tieng-anh-qua-hinh-anh/topic-21775.html");
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).data("query", "Java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(10000).post();
                Element es = doc.getElementsByClass("width_common folder_item_list").first();

                Elements datas = es.getElementsByClass("block_image_news width_common");

                for(Element element : datas){
                    String title = element.getElementsByClass("thumb").first().getElementsByAttribute("title").attr("title");
                    String image = element.getElementsByClass("thumb").first().getElementsByAttribute("src").attr("src");
                    String link  = element.getElementsByClass("thumb").first().getElementsByAttribute("href").attr("href");
                }

                Log.i("Data",String.valueOf(datas.size()));
                return doc.title();
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(String feed) {

        }
    }
}
