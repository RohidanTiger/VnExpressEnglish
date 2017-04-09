package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.view.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.BaseFragment;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.R;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.models.NewsObject;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.service.NewsRequest;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.utils.ConnectivityReceiver;

/**
 * Created by QuyDV on 4/5/17.
 */

public class NewsFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener{
    @BindView(R.id.reycle_news)
    RecyclerView mRecyclerViewNews;

    private ArrayList<NewsObject> listNews = new ArrayList<>();

    private LoaderManager.LoaderCallbacks<List<NewsObject>> newsListener
            = new LoaderManager.LoaderCallbacks<List<NewsObject>>() {
        @Override
        public Loader<List<NewsObject>> onCreateLoader(int id, Bundle args) {
            return new NewsRequest(context,"http://vnexpress.net/hoc-tieng-anh-qua-tin-nong/topic-21430.html");
        }
        @Override
        public void onLoadFinished(Loader<List<NewsObject>> loader, List<NewsObject> data) {
            context.hideLoading();
            listNews.addAll(data);
        }
        @Override
        public void onLoaderReset(Loader<List<NewsObject>> loader) {
            context.hideLoading();
        }
    };


    @Override
    protected int getViewContent() {
        return R.layout.news_fragment;
    }

    @Override
    protected void initUI() {
        //requestHtmlContent("http://vnexpress.net/hoc-tu-vung-tieng-anh-qua-hinh-anh/topic-21775.html");
        //new RetrieveFeedTask().execute("http://vnexpress.net/hoc-tieng-anh-qua-tin-nong/topic-21430.html");
        context.getSupportLoaderManager().initLoader(1, null, newsListener).forceLoad();
    }

    private void requestHtmlContent(String url){
        try {
            Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(10000).post();
            Log.i("Data",doc.data());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Toast.makeText(context,R.string.str_no_intternet,Toast.LENGTH_LONG).show();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).data("query", "Java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(10000).post();
                Element es = doc.getElementsByClass("width_common folder_item_list").first();

                Elements datas = es.getElementsByClass("block_image_news width_common");

                for(Element element : datas){
                    Element e = element.getElementsByClass("thumb").first();
                    String title = e.getElementsByAttribute("title").attr("title");
                    String image = e.getElementsByAttribute("src").attr("src");
                    String link  = e.getElementsByAttribute("href").attr("href");
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
