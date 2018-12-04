package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.framgia.bang.recyclerview.R;
import data.model.Language;
import java.util.ArrayList;

public class LanguageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE = 0;
    public static final int VIEW_LOADING = 1;
    private Context mContext;
    private ArrayList<Language> mLanguages;
    private boolean isLoading;
    private LoadMore mLoadMore;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public LanguageAdapter(Context context, ArrayList<Language> languages,
            RecyclerView recyclerView) {
        mContext = context;
        mLanguages = languages;

        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = manager.getItemCount();
                Log.d("aaa", "onScrolled: "+totalItemCount);
                lastVisibleItem = manager.findLastVisibleItemPosition();
                Log.d("aaa", "onScrolled1: "+lastVisibleItem);
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mLoadMore != null) {
                        mLoadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mLanguages.get(position) == null ? VIEW_LOADING : VIEW_TYPE;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setLoadMore(LoadMore loadMore) {
        mLoadMore = loadMore;
    }

    public void setLoaded() {
        isLoading = false;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);

            ViewHolder holder = new ViewHolder(v);
            return holder;
        } else if (viewType == VIEW_LOADING) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false);
            LoadHolder loadHolder = new LoadHolder(v);
            return loadHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Language language = mLanguages.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.add(language);
        }
        if (holder instanceof LoadHolder) {

            LoadHolder loadHolder = (LoadHolder) holder;
            loadHolder.mBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mLanguages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_Name;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Name = itemView.findViewById(R.id.txt_language);
        }

        public void add(Language language) {
            txt_Name.setText(language.getName().toString());
        }
    }

    public static class LoadHolder extends RecyclerView.ViewHolder {
        private ProgressBar mBar;

        public LoadHolder(View itemView) {
            super(itemView);
            mBar = itemView.findViewById(R.id.progress_load);
        }
    }
}
