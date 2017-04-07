package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.MainActivity;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.R;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.models.NewsObject;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/7/17.
 */

public class NewsAdapter extends RecyclerView.Adapter{

    private ArrayList<NewsObject> mDataSet;
    private MainActivity mContext;
    private OnItemClickListener listener;

    public NewsAdapter(MainActivity context, ArrayList<NewsObject> dataSet) {
        this.mDataSet = dataSet;
        this.mContext = context;
    }

    public void setmDataSet(ArrayList<NewsObject> mData) {
        this.mDataSet = mData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewsObject obj = mDataSet.get(position);
        ((NewsAdapter.ViewHolder) holder).textViewName.setText(obj.getTitle());
        PicassoLoader.getInstance(mContext).load(obj.getLink()).placeholder(R.drawable.bg_captcha).
                error(R.drawable.bg_captcha).into((((ViewHolder) holder).imgNews));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgNews;
        public TextView textViewName;

        public ViewHolder(View v) {
            super(v);
            imgNews = (ImageView) v.findViewById(R.id.imgNews);
            textViewName = (TextView) v.findViewById(R.id.txtName);
        }
    }

    public interface OnItemClickListener {
        void onClick(NewsObject obj);
    }
}
