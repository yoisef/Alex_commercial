package youssef.com.alex_commercial.forthyear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import youssef.com.alex_commercial.R;

/**
 * Created by mohamed on 14/04/2018.
 */

public class sectionadapter2 extends RecyclerView.Adapter<sectionadapter2.viewholder> {

    RecyclerView sectionrecycle;

    Context mycontext;
    String[]sectionlist={"شعبة المحاسبة","شعبة ادارة الاعمال","شعبة الاقتصاد","شعبة الدراسات المالية والجمركية","شعبة الاحصاء التطبيقي","شعبة نطم المعلومات","شعبة العلوم السياسية"};

    int[]sectionphoto={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven};

    public sectionadapter2(Context context)
    {
        this.mycontext=context;



    }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
       View sectionview= inflater.inflate(R.layout.sectionrow,parent,false);

        viewholder holder=new viewholder(sectionview);
        return holder;
    }

    @Override
    public int getItemCount() {
        return sectionlist.length;
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {

        holder.sectionnom.setText(sectionlist[position]);
        holder.sectionphoto.setImageResource(sectionphoto[position]);

    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView sectionnom;
        CircleImageView sectionphoto;

        public viewholder(View itemView) {
            super(itemView);

            sectionnom=(TextView)itemView.findViewById(R.id.sectionname);
            sectionphoto=(CircleImageView)itemView.findViewById(R.id.sectionjpg);
        }
    }
}
