package youssef.com.alex_commercial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mohamed on 05/04/2018.
 */

public class rec1adapter extends RecyclerView.Adapter<rec1adapter.myviewholder> {

    Context contextt;

    ArrayList<String> matrieal = new ArrayList<>();
    RecyclerView recv1, recv2;

    String dom, doce, doctyad, vevo, zezo, lezo;
    ArrayList<matriealanddoctor> arraylist;


    ArrayList<String> doctors = new ArrayList<>();
    int[] numbers = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference refmatrieal = database.getReference("yeat1").child("term1");

    public rec1adapter(Context context, View view) {
        this.contextt = context;
        recv1 = (RecyclerView) view.findViewById(R.id.recycle1);

        arraylist = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            refmatrieal.child("mat" + i).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, String> map = ((Map<String, String>) dataSnapshot.getValue());
                    if (map.get("matdoc") != null && map.get("matname") != null&&map.get("matjpg") !=null) {
                        String doctor = map.get("matdoc");
                        String name = map.get("matname");
                        String jpg=map.get("matjpg");
                        arraylist.add(new matriealanddoctor(name, doctor,jpg));
                        recv1.getAdapter().notifyDataSetChanged();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }


    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) contextt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recone, parent, false);

        myviewholder holder = new myviewholder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(final myviewholder holder, final int position) {
        matriealanddoctor exmple = arraylist.get(position);


        holder.doctor.setText(exmple.doctor);
        holder.matrieal.setText(exmple.matrieal);
        Glide.with(contextt)
                .load(exmple.photo)
                .into(holder.matphoto);
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        Intent site, chooser;
                        site = new Intent(Intent.ACTION_VIEW);
                        site.setData(Uri.parse("http://www.mediafire.com/file/rwjsae1qjrbott1/%D8%A3%D9%88%D8%B1%D8%A7%D9%82+%D9%85%D8%B9%D9%84%D9%88%D9%85%D8%A7%D8%AA+-+%D9%85%D8%A8%D8%A7%D8%AF%D8%A6+%D8%A7%D9%84%D9%85%D8%AD%D8%A7%D8%B3%D8%A8%D8%A9+%D8%A7%D9%84%D9%85%D8%A7%D9%84%D9%8A%D8%A9.rar"));
                        chooser = Intent.createChooser(site, "launchsite");
                        contextt.startActivity(chooser);
                        break;
                    case 1:
                        Intent z, a;
                        z = new Intent(Intent.ACTION_VIEW);
                        z.setData(Uri.parse("https://www.google.com.eg/?gfe_rd=cr&dcr=0&ei=3DayWp60C9CFhgPysaqYBA"));
                        a = Intent.createChooser(z, "launchsite");
                        contextt.startActivity(a);
                        break;
                }
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0: {
                        Intent i = new Intent(contextt, Comment.class);
                        contextt.startActivity(i);
                    }
                }
            }
        });





    }


    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView matrieal, doctor;
        LinearLayout comment, download;
        ImageView matphoto;

        public myviewholder(View itemView) {
            super(itemView);

           matrieal = (TextView) itemView.findViewById(R.id.matriealname);
            doctor = (TextView) itemView.findViewById(R.id.matriealdoc);
            comment = (LinearLayout) itemView.findViewById(R.id.commentlayout);
            download = (LinearLayout) itemView.findViewById(R.id.downloadlayout);
           matphoto = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}
