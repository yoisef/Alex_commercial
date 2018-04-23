package youssef.com.alex_commercial;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class studytables extends AppCompatActivity {


    RecyclerView tablesrec;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studytables);

        tablesrec = (RecyclerView) findViewById(R.id.tablesrecycle);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        tablesrec.setLayoutManager(manager);
        storage=FirebaseStorage.getInstance();
        final StorageReference reference=storage.getReference("SC_1_3.pdf");



        tablesadapter adapter = new tablesadapter(this);
        tablesrec.setAdapter(adapter);
        tablesrec.addOnItemTouchListener(
                new RecyclerItemClickListener(this, tablesrec, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0: {
                                final String direc = getApplicationContext().getExternalCacheDir() + "/mydown/";
                                File myfile = new File(direc);


                                final long ONE_MEGABYTE = 1024 * 1024;
                                reference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        FileOutputStream fileOutputStream = null;
                                        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                        File folder=new File(file,"sc.pdf");
                                        try {

                                            fileOutputStream = new FileOutputStream(folder);
                                            fileOutputStream.write(bytes);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } finally {
                                            try {
                                                if (fileOutputStream!=null)
                                                fileOutputStream.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        Toast.makeText(getApplicationContext(),"saved at"+file+"/sc1.pdf",Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });

                            }
                            case 1:

                            case 5: {

                            }
                            default:Toast.makeText(getApplicationContext(),"No data yet",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }
}
