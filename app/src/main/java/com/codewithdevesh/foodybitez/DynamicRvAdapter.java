package com.codewithdevesh.foodybitez;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithdevesh.foodybitez.DRVinterface.LoadMore;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder{
    public ProgressBar pb;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        pb = itemView.findViewById(R.id.prog_bar);
    }
}
class ItemViewHolder extends RecyclerView.ViewHolder{
    public TextView name;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.food_name);
    }
}

public class DynamicRvAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    LoadMore loadMore;
    boolean isLoading;
//    Activity activity;
    Fragment fragment;
    List<DynamicRvModel> items;
    int visibleThreshold=5;
    int lastVisibleItem,totalItemCount;

//    public DynamicRvAdapter(RecyclerView recyclerView,Activity activity,List<DynamicRvModel>items) {
//        this.activity = activity;
//        this.items=items;
//    }

    public DynamicRvAdapter(List<DynamicRvModel> items) {
        this.items = items;
    }

    public DynamicRvAdapter(RecyclerView recyclerView,Fragment fragment,List<DynamicRvModel>items) {
        this.fragment = fragment;
        this.items=items;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount<=(linearLayoutManager.findLastVisibleItemPosition()) ){
                    if(loadMore!=null){
                        loadMore.onLoadMore();
                        isLoading=true;

                    }
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position)==null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore){
        this.loadMore=loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==VIEW_TYPE_ITEM){
           View v = LayoutInflater.from(fragment.requireContext()).inflate(R.layout.dynamic_rv_item_layout,parent,false);
           return new LoadingViewHolder(v);
       }
       else if(viewType==VIEW_TYPE_LOADING){
           View v = LayoutInflater.from(fragment.requireContext()).inflate(R.layout.dynamic_rv_progress_bar,parent,false);
           return new LoadingViewHolder(v);
       }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder){
            DynamicRvModel item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(items.get(position).getName());
        }
        else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public  void setLoaded(){
        isLoading=false;
    }
}
