package youssef.com.alex_commercial.thirdy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import youssef.com.alex_commercial.R;

/**
 * Created by mohamed on 02/04/2018.
 */

public class termseond extends Fragment {


    RecyclerView recyclerView;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return   inflater.inflate(R.layout.trmsec,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext();

        recyclerView=(RecyclerView)view.findViewById(R.id.recycle2);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        rec2adapter adapter=new rec2adapter(this.getActivity(),this.getView());
        recyclerView.setAdapter(adapter);
    }
}
