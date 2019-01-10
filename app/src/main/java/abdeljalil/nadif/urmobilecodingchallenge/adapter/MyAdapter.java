package abdeljalil.nadif.urmobilecodingchallenge.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import abdeljalil.nadif.urmobilecodingchallenge.R;
import abdeljalil.nadif.urmobilecodingchallenge.model.Repo;
import abdeljalil.nadif.urmobilecodingchallenge.service.ILoadMore;

/**
 * This LoadingViewHolder Class will load get the progressBar view when loading next pages
 */

class LoadingViewHolder extends RecyclerView.ViewHolder{
    public ProgressBar progressBar;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }
}

/**
 * This Item View Holder will set the view that contains the information about each repo
 */

class ItemViewHolder extends RecyclerView.ViewHolder{
    public TextView repoName, repoDesc, repoOwner, repoStars;
    public ImageView repoOwnerAvatar;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        repoName = (TextView) itemView.findViewById(R.id.repoName);
        repoDesc = (TextView) itemView.findViewById(R.id.repoDesc);
        repoOwner = (TextView) itemView.findViewById(R.id.ownerName);
        repoStars = (TextView) itemView.findViewById(R.id.numStars);
        repoOwnerAvatar = (ImageView) itemView.findViewById(R.id.ownerAvatar);
    }
}

/**
 * The adapter that'll add the new view to our Recycler View
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<Repo> repos;
    int visibleThreashold = 5;
    int lastVisibleItem, totalItemCount;

    public MyAdapter(RecyclerView recyclerView, Activity activity, List<Repo> repos) {
        this.activity = activity;
        this.repos = repos;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreashold)){
                    if(loadMore != null){
                        loadMore.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return repos.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.new_item, parent, false);
            return new ItemViewHolder(view);
        }else if(viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            Repo repo = repos.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.repoName.setText(repos.get(position).getName());
            viewHolder.repoDesc.setText(repos.get(position).getDescription());
            viewHolder.repoOwner.setText(repos.get(position).getOwner().getName());
            //viewHolder.repoOwnerAvatar.setImageBitmap( getImageBitmap(repos.get(position).getOwner().getAvatar()));
            double nbr = repos.get(position).getNumberStars()/1000.00;
            String stars = nbr >= 1 ? (String.valueOf(nbr)+" k") : String.valueOf(repos.get(position).getNumberStars());
            viewHolder.repoStars.setText(stars);
        }

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    /**
     * Images are received as an URL, so with the getImageBitmap we'll convert them
     * to extract bitmap images and add them to the view
     * @param url
     * @return a bitmap image that will be set to the imageView
     */

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("IMAGE BITMAP", "Error getting bitmap", e);
        }
        return bm;
    }
}

