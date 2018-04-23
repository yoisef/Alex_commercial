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

import youssef.com.alex_commercial.firsty.Fyear;
import youssef.com.alex_commercial.forthyear.section2;
import youssef.com.alex_commercial.secondy.Syear;
import youssef.com.alex_commercial.thirdy.section;

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
        menu fu=new menu();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fu).commit();
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
                    case R.id.three:{
                       // Tyear as=new Tyear();
                        section mysec=new section();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,mysec).commit();
                        break;
                    }
                    case R.id.four:{
                        section2 sec=new section2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,sec).commit();
                    }
                }
                return true;
            }
        });



    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        menu fu=new menu();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fu).addToBackStack("menuf").commit();
    }
    */

}







