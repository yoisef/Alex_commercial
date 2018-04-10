package youssef.com.alex_commercial;

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

public class Comment extends AppCompatActivity {

    RecyclerView commentrecycle;
    ImageView sendimage;
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

        comlist = new ArrayList<>();

        final ArrayList<String> keys = new ArrayList<>();

        mauth = FirebaseAuth.getInstance();
        refcomments = firebaseDatabase.getReference("Comments");


        usercomment = (EditText) findViewById(R.id.usercomment);
        commentrecycle = (RecyclerView) findViewById(R.id.recyclecomment);
        // final commentadapter adapter = new commentadapter(this, comlist);






        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        commentrecycle.setLayoutManager(linearLayoutManager);


        sendimage = (ImageView) findViewById(R.id.sendimg);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

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
                    if (user.getDisplayName() != null) {
                        usernamee = user.getDisplayName();
                        newcomment.child("usernom").setValue(usernamee);
                    }
                    if (user.getDisplayName()==null) {
                        newcomment.child("usernom").setValue("unknown");
                    }
                    if (user.getPhotoUrl() != null) {
                        photourl = user.getPhotoUrl().toString();
                        newcomment.child("userphot").setValue(photourl);

                    }
                    if (user.getPhotoUrl()==null){
                        String defphoto="http://www.abjadarabi.com/web/wp-content/uploads/2017/11/636036537606891279_5d3188.png";
                        newcomment.child("userphot").setValue(defphoto);
                    }


                }


            }


        });



    }


    @Override
    protected void onStart() {
        super.onStart();


    /**
     * Created by mohamed on 14/03/2018.
     */

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
