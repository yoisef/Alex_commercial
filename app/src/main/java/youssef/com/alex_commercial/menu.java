package youssef.com.alex_commercial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mohamed on 02/04/2018.
 */

public class menu extends Fragment {
    Button n;
    FirebaseAuth mauth;
    RecyclerView recyclerView;
    Context context;
    ImageView cover;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext();
        String link="http://onaeg.com/wp-content/uploads/2015/07/alex1.jpg";

        cover=(ImageView) view.findViewById(R.id.coverjpg);
        Glide.with(context)
                .load(link)
                .into(cover);


        recyclerView=(RecyclerView)view.findViewById(R.id.menurec);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
      //  menuadapter adapter=new menuadapter(context);
      //  recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseAuth mauth=FirebaseAuth.getInstance();
        menuadapter adapter=new menuadapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Toast.makeText(context,"hi",Toast.LENGTH_SHORT).show();
                                break;
                            case 1:Toast.makeText(context,"الحمد لله",Toast.LENGTH_SHORT).show();
                                break;
                            case 5:{
                                mauth.signOut();
                                Intent i=new Intent(context,MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                break;
                            }
                            default:Toast.makeText(context,"العادي",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
}