package youssef.com.alex_commercial.thirdy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import youssef.com.alex_commercial.R;
import youssef.com.alex_commercial.RecyclerItemClickListener;

/**
 * Created by mohamed on 14/04/2018.
 */

public class section extends Fragment {

    RecyclerView mysection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sectionlayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView sectionphoto=(ImageView)view.findViewById(R.id.sectionview) ;
        String section="http://2.bp.blogspot.com/-_XGaBvM7HFU/T00qOG3mH9I/AAAAAAAAACw/QGSpZq9GAr0/s1600/juo32.jpg";
        Glide.with(this)
                .load(section)
                .into(sectionphoto);

        mysection = (RecyclerView) view.findViewById(R.id.sectionrec);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        mysection.setLayoutManager(manager);
        sectionadapter sectionadapter = new sectionadapter(view.getContext());
        mysection.setAdapter(sectionadapter);

        mysection.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), mysection, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position) {

                                default:
                                    Toast.makeText(getContext(),"No data yet",Toast.LENGTH_SHORT).show();
                                    break;


                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }


                })
        );
    }
}

