package youssef.com.alex_commercial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mohamed on 17/04/2018.
 */

public class tablesadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int normalview = 0;
    public static final int sectionview = 1;

    String[]tablespoint={"جداول الفرقة الاولي","جداول الفرقة الثانية"};
    String[]tablespoint2={"ة","k","جداول الفرقة الثالثة","جداول الفرقة الرابعة"};
    Spinner sections;
    ArrayAdapter<CharSequence>adapter;

    Context cont;
    public tablesadapter(Context context)
    {
        this.cont=context;

        adapter = ArrayAdapter.createFromResource(context, R.array.sectionname, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // sections.setAdapter(adapter);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == normalview) {
            View headr = LayoutInflater.from(cont.getApplicationContext()).inflate(R.layout.tablesf, parent,false);
            return new tablesadapter.normalviewholder(headr);
        } else {
            View normal = LayoutInflater.from(cont.getApplicationContext()).inflate(R.layout.tabes2, parent,false);
            return new tablesadapter.sectionviewholder(normal);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final int itemViewType=getItemViewType(position);

        switch (itemViewType)
        {
            case normalview:( (normalviewholder) holder).tables.setText(tablespoint[position]);
                break;
            case sectionview:{
                ((sectionviewholder) holder).table.setText(tablespoint2[position]);
                ((sectionviewholder) holder).myspinner.setAdapter(adapter);

            }
                break;
        }



    }



    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return normalview;
            case 1:
                return normalview;
            case 2:
                return sectionview;
            case 3:
                return sectionview;
            default:return normalview;
        }

    }

    @Override
    public int getItemCount() {
        return tablespoint2.length;
    }

    class normalviewholder extends RecyclerView.ViewHolder{

        TextView tables;

        public normalviewholder(View itemView) {
            super(itemView);
            tables=(TextView)itemView.findViewById(R.id.tablesyear);
        }
    }

    class sectionviewholder extends RecyclerView.ViewHolder{

        TextView table;
        Spinner myspinner;


        public sectionviewholder(View itemView) {
            super(itemView);
            table=(TextView)itemView.findViewById(R.id.table4);
            myspinner=(Spinner)itemView.findViewById(R.id.spinnersec);


        }
    }
}