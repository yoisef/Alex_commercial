package youssef.com.alex_commercial;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.roughike.bottombar.BottomBar;

public class mainapp extends AppCompatActivity {


    ImageView menuimage, second, third, four, first;
    BottomBar bottomBar;
    Fragment year1, year2, year3, year4;
    FragmentManager manager=getSupportFragmentManager();
    FragmentTransaction transaction;
    BottomNavigationView navigationView;
    MenuItem itemh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainapp);




        navigationView=(BottomNavigationView)findViewById(R.id.botomview);
       navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Home:{

                        menu fu=new menu();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fu).commit();
                        break;
                    }
                    case R.id.one:{
                        Fyear su=new Fyear();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,su).commit();
                        break;
                    }
                    case R.id.two:{
                        Syear tu=new Syear();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,tu).commit();
                        break;
                    }
                }
                return true;
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        menu fu=new menu();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fu).commit();
    }
}







