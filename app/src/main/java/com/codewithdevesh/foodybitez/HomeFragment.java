package com.codewithdevesh.foodybitez;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codewithdevesh.foodybitez.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private staticRvAdapter staticRvAdapter;

    List<DynamicRvModel> items = new ArrayList<>();
    DynamicRvAdapter dynamicRvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<staticRvModel>item = new ArrayList<>();
        item.add(new staticRvModel(R.drawable.pizza,"Pizza"));
        item.add(new staticRvModel(R.drawable.burger,"Burger"));
        item.add(new staticRvModel(R.drawable.fires,"Fries"));
        item.add(new staticRvModel(R.drawable.sandwich,"Sandwich"));
        item.add(new staticRvModel(R.drawable.drinks,"Drinks"));
        recyclerView = view.findViewById(R.id.recylerview1);
        staticRvAdapter = new staticRvAdapter(item);
        staticRvAdapter.setHasStableIds(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//        items.add(new DynamicRvModel("Burger"));
//
//        RecyclerView drv = view.findViewById(R.id.recylerview2);
//        drv.setLayoutManager(new LinearLayoutManager(requireContext()));
//        dynamicRvAdapter = new DynamicRvAdapter(drv,this,items);
//        drv.setAdapter(dynamicRvAdapter);
//
//        dynamicRvAdapter.setLoadMore(new LoadMore() {
//            @Override
//            public void onLoadMore() {
//                if(items.size()<=10){
//                    item.add(null);
//                    dynamicRvAdapter.notifyItemInserted(items.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            items.remove(items.size()-1);
//                            dynamicRvAdapter.notifyItemRemoved(items.size());
//
//                            int index = items.size();
//                            int end = index+10;
//                            for(int i=index;i<end;i++){
//                                String name = UUID.randomUUID().toString();
//                                DynamicRvModel item = new DynamicRvModel(name);
//                                items.add(item);
//                            }
//                            dynamicRvAdapter.notifyDataSetChanged();
//                            dynamicRvAdapter.setLoaded();
//                        }
//                    },2000);
//
//                }
//                else{
//                    Toast.makeText(requireContext(), "Data Completed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        // Inflate the layout for this fragment
        return view;
    }
}