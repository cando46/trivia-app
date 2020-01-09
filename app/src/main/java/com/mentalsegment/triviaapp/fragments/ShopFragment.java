package com.mentalsegment.triviaapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mentalsegment.triviaapp.ItemModel;
import com.mentalsegment.triviaapp.R;
import com.mentalsegment.triviaapp.adapters.ShopItemAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<ItemModel> shopItems;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rv_shop_itemRV);
        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rvLayoutManager);
        shopItems=new ArrayList<>();
        shopItems.add(new ItemModel(R.drawable.classic5050,"Half/Half Lifeline","500"));
        shopItems.add(new ItemModel(R.drawable.classic_ata,"Ask To Audience","750"));
        shopItems.add(new ItemModel(R.drawable.skip_shuffle,"Skip Question","1500"));
        shopItems.add(new ItemModel(R.drawable.double_dip,"Double Dip","1000"));

        ShopItemAdapter shopItemAdapter=new ShopItemAdapter(getContext(),shopItems);
        recyclerView.setAdapter(shopItemAdapter);
    }

}
