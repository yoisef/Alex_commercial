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
        menuadapter adapter=new menuadapter(context);
       recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseAuth mauth=FirebaseAuth.getInstance();
        final menuadapter adapter=new menuadapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                               Intent i=new Intent(context,Userprofile.class);
                                startActivity(i);
                                break;
                            case 1:Intent j=new Intent(context,studytables.class);
                                startActivity(j);
                                break;
                            case 5:{
                                mauth.signOut();
                                getActivity().finish();
                                Intent a=new Intent(context,MainActivity.class);
                                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(a);
                                break;
                            }
                            case 4:Intent f=new Intent(context,Aboutme.class);
                                startActivity(f);
                                break;
                            case 2:Intent l=new Intent(context,studytables.class);
                                startActivity(l);
                                break;
                            default:Toast.makeText(context,"No data yet",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
}