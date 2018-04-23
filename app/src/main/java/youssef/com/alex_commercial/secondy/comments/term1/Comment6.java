package youssef.com.alex_commercial.secondy.comments.term1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import youssef.com.alex_commercial.CommentModel;
import youssef.com.alex_commercial.firsty.comments.Usermodel;
import youssef.com.alex_commercial.R;

public class Comment6 extends AppCompatActivity {

    RecyclerView commentrecycle;
    ImageView sendimage,backbutton;
    FirebaseAuth mauth;
    EditText usercomment;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference refcomments;
    ArrayList<CommentModel> comlist;
    DatabaseReference newcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        backbutton=(ImageView)findViewById(R.id.goback);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
        comlist = new ArrayList<>();

        final ArrayList<String> keys = new ArrayList<>();

        mauth = FirebaseAuth.getInstance();
        refcomments = firebaseDatabase.getReference("Comments").child("year2").child("term1").child("com6");


        usercomment = (EditText) findViewById(R.id.usercomment);
        commentrecycle = (RecyclerView) findViewById(R.id.recyclecomment);
        // final commentadapter adapter = new commentadapter(this, comlist);






        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        commentrecycle.setLayoutManager(linearLayoutManager);


        sendimage = (ImageView) findViewById(R.id.sendimg);
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        sendimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newcomment = refcomments.push();
                String usercom = usercomment.getText().toString();
                newcomment.child("usercom").setValue(usercom);

                usercomment.setText(null);


                String photourl;
                String usernamee;
                String Deafaulturl;
                FirebaseUser user = mauth.getCurrentUser();
                if (user != null) {
                    DatabaseReference reference = firebaseDatabase.getReference("users").child(user.getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Usermodel example = dataSnapshot.getValue(Usermodel.class);
                            newcomment.child("usernom").setValue(example.name);
                            newcomment.child("userphot").setValue(example.photo);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        // sadapter myadapter=new adapter(this);
        // commentrecycle.etAdapter(myadapter);


    }
/*
    @Override
    protected void onStart() {
        super.onStart();



        class adapter extends RecyclerView.Adapter<adapter.viewholder>{
            Context context;
            ArrayList<CommentModel> commentlist=new ArrayList<>();


            public adapter(Context con)
            {
                this.context=con;
                refcomments.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        CommentModel model=dataSnapshot.getValue(CommentModel.class);
                        commentlist.add(model);
                        commentrecycle.getAdapter().notifyDataSetChanged();
                        commentrecycle.smoothScrollToPosition(commentlist.size()-1);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.commentrow, parent, false);

                adapter.viewholder holder = new adapter.viewholder(view);

                return holder;
            }

            @Override
            public void onBindViewHolder(viewholder holder, int position) {

                CommentModel mycomment=commentlist.get(position);

                holder.usercomment.setText(mycomment.usercom);
                holder.username.setText(mycomment.usernom);
                Glide.with(context)
                        .load(mycomment.userphot)
                        .into(holder.userpphoto);

            }



            @Override
            public int getItemCount() {
                return commentlist.size();
            }



             class viewholder extends RecyclerView.ViewHolder  {

                TextView username,usercomment;
                CircleImageView userpphoto;
                View view;


                public viewholder(View itemview)
                {
                    super(itemview);
                    this.view=itemview;
                    username=(TextView)itemview.findViewById(R.id.nameshow);
                    usercomment=(TextView)itemview.findViewById(R.id.commentshow);
                    userpphoto=(CircleImageView)itemview.findViewById(R.id.photoshow);

                }
            }
        }

        commentrecycle.setAdapter(new adapter(this));
    }



*/




    @Override
    protected void onStart() {
        super.onStart();




        FirebaseRecyclerAdapter<CommentModel, viewholder> fireadapter = new FirebaseRecyclerAdapter<CommentModel, viewholder>(
                CommentModel.class,
                R.layout.commentrow,
                viewholder.class,
                refcomments) {


            @Override
            protected void populateViewHolder(viewholder viewHolder, CommentModel model, int position) {
                viewHolder.username.setText(model.getUsernom());
                viewHolder.usercomment.setText(model.getUsercom());
                Glide.with(getApplicationContext())
                        .load(model.userphot)
                        .into(viewHolder.userpphoto);



            }


        };
        commentrecycle.setAdapter(fireadapter);
        refcomments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i= ((int) dataSnapshot.getChildrenCount());

                commentrecycle.smoothScrollToPosition(i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static class viewholder extends RecyclerView.ViewHolder  {

        TextView username,usercomment;
        CircleImageView userpphoto;
        View view;


        public viewholder(View itemview)
        {
            super(itemview);
            this.view=itemview;
            username=(TextView)itemview.findViewById(R.id.nameshow);
            usercomment=(TextView)itemview.findViewById(R.id.commentshow);
            userpphoto=(CircleImageView)itemview.findViewById(R.id.photoshow);

        }
    }


}
