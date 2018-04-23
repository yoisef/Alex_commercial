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
import android.widget.PopupWindow;

import youssef.com.alex_commercial.R;

/**
 * Created by mohamed on 02/04/2018.
 */

public class termone extends Fragment {

    RecyclerView recyclerView;
    Context context;
    RecyclerView.LayoutManager manager;
    PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.termawl,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext();

        popupWindow=new PopupWindow(context);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycle1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        rec1adapter adapter=new rec1adapter(this.getActivity(),this.getView());
        recyclerView.setAdapter(adapter);
    }

   
}
