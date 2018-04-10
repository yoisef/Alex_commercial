package youssef.com.alex_commercial;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mohamed on 08/04/2018.
 */

public class menuadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int Headerview = 0;
    public static final int normalview = 1;
    List<String> mylist= Arrays.asList("a","b","c","d","e");

    FirebaseAuth myauth = FirebaseAuth.getInstance();
    Context con;
    String[] titles = {",","جداول الدراسة","جداول الامتحانات", "جداول السكاشن", "About us","تسجيل الخروج"};
    int [] icons={1,R.drawable.table,R.drawable.exam,R.drawable.tablez,R.drawable.aboutu,R.drawable.exit};

    public menuadapter(Context context) {
        this.con = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Headerview) {
            View headr = LayoutInflater.from(con.getApplicationContext()).inflate(R.layout.rowmenu, parent,false);
            return new Myviewheadr(headr);
        } else {
            View normal = LayoutInflater.from(con.getApplicationContext()).inflate(R.layout.normallayout, parent,false);
            return new Normalview(normal);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        String userid;

        final int itemType = getItemViewType(position);
        FirebaseUser user = myauth.getCurrentUser();
        int pos = holder.getAdapterPosition();
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("users");
        if (user!=null)
        {
             userid=user.getUid();
            if (itemType == Headerview) {

                reference.child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                      Userinfo currentuser=  dataSnapshot.getValue(Userinfo.class);

                        ((Myviewheadr) holder).name.setText(currentuser.name);

                        Glide.with(con)
                                .load(currentuser.photo)
                                .into(((Myviewheadr) holder).personphoto);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            else if (itemType == normalview) {
                ((Normalview) holder).title.setText(titles[pos]);
                ((Normalview) holder).icon.setImageResource(icons[pos]);
            }
        }

        }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Headerview;
        } else {
            return normalview;
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class Myviewheadr extends RecyclerView.ViewHolder {

        CircleImageView personphoto;
        TextView name, adjust;

        public Myviewheadr(View itemView) {
            super(itemView);
            personphoto = (CircleImageView) itemView.findViewById(R.id.personphoto);
            name = (TextView) itemView.findViewById(R.id.username);
            adjust = (TextView) itemView.findViewById(R.id.adjustinfo);
        }
    }

    class Normalview extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        public Normalview(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.titlee);
            icon = (ImageView) itemView.findViewById(R.id.iconrow);
        }
    }
}

