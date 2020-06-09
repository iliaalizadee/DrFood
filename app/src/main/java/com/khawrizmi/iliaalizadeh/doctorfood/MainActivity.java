package com.khawrizmi.iliaalizadeh.doctorfood;

import androidx.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        bnd=findViewById(R.id.bnd);
        AdviceFragment adviceFragment=new AdviceFragment();
        firstset(adviceFragment);

        bnd.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.sended){
                    SendedFragment sendedFragment=new SendedFragment();
                    setpage(sendedFragment);
                    return true;
                }
                if(item.getItemId()==R.id.advice){
                    AdviceFragment adviceFragment=new AdviceFragment();
                    setpage(adviceFragment);
                    return true;
                }
                if(item.getItemId()==R.id.account){
                    accountFragment accountFragment=new accountFragment();
                    setpage(accountFragment);
                    return true;
                }
                return false;
            }
        });

    }

    public void setpage(Fragment fragment){

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frgholder,fragment);
        ft.commit();

    }
    public void firstset(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.frgholder,fragment);
        ft.commit();

    }
}


