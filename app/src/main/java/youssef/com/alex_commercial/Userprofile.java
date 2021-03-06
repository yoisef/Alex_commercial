package youssef.com.alex_commercial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Userprofile extends AppCompatActivity {
    CircleImageView proilephoto;
    Button saveuserinfo;

    EditText Fname, Lname, Birthday;
    TextView uploadphoto, profilenamee;
    String downloadedurl, selectedteam;
    Uri uriprofileimage;
    Button savebutton;
    ProgressBar progressBar;
    private static final int image = 101;
    private FirebaseAuth mAuth;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    Spinner myspinner;
    ArrayAdapter<CharSequence> adapter;
    DatabaseReference userkeys, userimg;
    FirebaseStorage firebaseStorage;
    StorageReference imgref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference usercommentref;

    String username, key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        Fname = (EditText) findViewById(R.id.firstn);

        Lname = (EditText) findViewById(R.id.Lastn);
        Birthday = (EditText) findViewById(R.id.birthday);
        uploadphoto = (TextView) findViewById(R.id.addphoto);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.proimage);
        saveuserinfo = (Button) findViewById(R.id.saveinfo);
        database = FirebaseDatabase.getInstance();


        userkeys = database.getReference("userkeys");
        userimg = database.getReference("userimg");

        usercommentref = database.getReference("usercomment");

        firebaseStorage = FirebaseStorage.getInstance();


        proilephoto = (CircleImageView) findViewById(R.id.photoshow);
        myspinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.studyteams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedteam = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                Intent chooser = Intent.createChooser(intent, "choose Image");
                startActivityForResult(chooser, image);
            }
        });

        saveuserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushuser();
                finish();
                Intent i=new Intent(Userprofile.this,mainapp.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


        MyEditTextDatePicker editTextDatePicker = new MyEditTextDatePicker(this, Birthday.getId());


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == image && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriprofileimage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileimage);

                proilephoto.setBackground(null);
                proilephoto.setImageBitmap(bitmap);

                uploadimagetofirebase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadimagetofirebase() {

        StorageReference profileimageref = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");


        if (uriprofileimage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileimageref.putFile(uriprofileimage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            downloadedurl = downloadUrl.toString();
                            Toast.makeText(Userprofile.this, "photo updated", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Userprofile.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }


    private void pushuser()
    {

        String username=Fname.getText().toString()+" "+Lname.getText().toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("users");
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseUser cuuser=mauth.getCurrentUser();
        if (cuuser!=null)
        {
            String id= cuuser.getUid();
            reference.child(id).child("name").setValue(username);
            reference.child(id).child("photo").setValue(downloadedurl);
        }


    }




}