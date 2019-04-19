package com.playfun.pcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private List<State> states = new ArrayList();
    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        countriesList =  findViewById(R.id.articlesList);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if(position == 0)
                {
                    Intent appInfo = new Intent(v.getContext(), LessonActivity.class);
                    startActivity(appInfo);
                } else if(position == 1)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson2Activity.class);
                    startActivity(appInfo);
                }else if(position == 2)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson3Activity.class);
                    startActivity(appInfo);
                }else if(position == 3)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson4Activity.class);
                    startActivity(appInfo);
                }else if(position == 4)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson5Activity.class);
                    startActivity(appInfo);
                }else if(position == 5)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson6Activity.class);
                    startActivity(appInfo);
                }else if(position == 6)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson7Activity.class);
                    startActivity(appInfo);
                }else if(position == 7)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson8Activity.class);
                    startActivity(appInfo);
                }else if(position == 8)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson9Activity.class);
                    startActivity(appInfo);
                }else if(position == 9)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson10Activity.class);
                    startActivity(appInfo);
                }else if(position == 10)
                {
                    Intent appInfo = new Intent(v.getContext(), Lesson11Activity.class);
                    startActivity(appInfo);
                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);

    }

    private void setInitialData(){

        states.add(new State ("Урок 1-й.", "Как следить за телодвижениями", R.drawable.pic1));
        states.add(new State ("Урок 2-й.", "Охраняем ваш банкролл", R.drawable.pic2));
        states.add(new State ("Урок 3-й.", "Основные подсказки", R.drawable.pic3));
        states.add(new State ("Урок 4-й.", "Поведение в сложных ситуациях", R.drawable.pic4));
        states.add(new State ("Урок 5-й.", "Как \"рекламировать\" себя в покере", R.drawable.pic5));
        states.add(new State ("Урок 6-й.", "Как добиться того, чтобы игроки со слабой рукой принимали ваши ставки", R.drawable.pic6));
        states.add(new State ("Урок 7-й.", "Подбираем себе имидж", R.drawable.pic7));
        states.add(new State ("Урок 8-й.", "Когда пора скидывать сильные карты", R.drawable.pic8));
        states.add(new State ("Урок 9-й.", "Профессиональные хитрости", R.drawable.pic9));
        states.add(new State ("Урок 10-й.", "Когда не поднимать?", R.drawable.pic10));
        states.add(new State ("Урок 11-й.", "Слабые шансы тоже считаются", R.drawable.pic11));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Меню сверху - удалить
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //Боковое меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.art1) {
            Intent appInfo = new Intent(this, LessonActivity.class);
            startActivity(appInfo);
        } else if (id == R.id.art2) {
            Intent appInfo = new Intent(this, Lesson2Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art3) {
            Intent appInfo = new Intent(this, Lesson3Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art4) {
            Intent appInfo = new Intent(this, Lesson4Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art5) {
            Intent appInfo = new Intent(this, Lesson5Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art6) {
            Intent appInfo = new Intent(this, Lesson6Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art7) {
            Intent appInfo = new Intent(this, Lesson7Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art8) {
            Intent appInfo = new Intent(this, Lesson8Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art9) {
            Intent appInfo = new Intent(this, Lesson9Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art10) {
            Intent appInfo = new Intent(this, Lesson10Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.art11) {
            Intent appInfo = new Intent(this, Lesson11Activity.class);
            startActivity(appInfo);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
