package poker.comstartlearnhowtoplay;

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

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private List<State> states = new ArrayList();
    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
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
                    Intent appInfo = new Intent(v.getContext(), articleActivity.class);
                    startActivity(appInfo);
                } else if(position == 1)
                {
                    Intent appInfo = new Intent(v.getContext(), article2Activity.class);
                    startActivity(appInfo);
                }else if(position == 2)
                {
                    Intent appInfo = new Intent(v.getContext(), article3Activity.class);
                    startActivity(appInfo);
                }else if(position == 3)
                {
                    Intent appInfo = new Intent(v.getContext(), article4Activity.class);
                    startActivity(appInfo);
                }else if(position == 4)
                {
                    Intent appInfo = new Intent(v.getContext(), article5Activity.class);
                    startActivity(appInfo);
                }else if(position == 5)
                {
                    Intent appInfo = new Intent(v.getContext(), article6Activity.class);
                    startActivity(appInfo);
                }else if(position == 6)
                {
                    Intent appInfo = new Intent(v.getContext(), article7Activity.class);
                    startActivity(appInfo);
                }else if(position == 7)
                {
                    Intent appInfo = new Intent(v.getContext(), article8Activity.class);
                    startActivity(appInfo);
                }else if(position == 8)
                {
                    Intent appInfo = new Intent(v.getContext(), article9Activity.class);
                    startActivity(appInfo);
                }else if(position == 9)
                {
                    Intent appInfo = new Intent(v.getContext(), article10Activity.class);
                    startActivity(appInfo);
                }else if(position == 10)
                {
                    Intent appInfo = new Intent(v.getContext(), article11Activity.class);
                    startActivity(appInfo);
                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);

    }

    private void setInitialData(){

        states.add(new State ("Урок 1-й.", "Как следить за телодвижениями", R.drawable.s1));
        states.add(new State ("Урок 2-й.", "Охраняем ваш банкролл", R.drawable.s2));
        states.add(new State ("Урок 3-й.", "Основные подсказки", R.drawable.s3));
        states.add(new State ("Урок 4-й.", "Поведение в сложных ситуациях", R.drawable.s4));
        states.add(new State ("Урок 5-й.", "Как \"рекламировать\" себя в покере", R.drawable.s5));
        states.add(new State ("Урок 6-й.", "Как добиться того, чтобы игроки со слабой рукой принимали ваши ставки", R.drawable.s6));
        states.add(new State ("Урок 7-й.", "Подбираем себе имидж", R.drawable.s7));
        states.add(new State ("Урок 8-й.", "Когда пора скидывать сильные карты", R.drawable.s8));
        states.add(new State ("Урок 9-й.", "Профессиональные хитрости", R.drawable.s9));
        states.add(new State ("Урок 10-й.", "Когда не поднимать?", R.drawable.s10));
        states.add(new State ("Урок 11-й.", "Слабые шансы тоже считаются", R.drawable.s11));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

         if (id == R.id.ma1) {
            Intent appInfo = new Intent(this, articleActivity.class);
            startActivity(appInfo);
        } else if (id == R.id.ma2) {
            Intent appInfo = new Intent(this, article2Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma3) {
            Intent appInfo = new Intent(this, article3Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma4) {
            Intent appInfo = new Intent(this, article4Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma5) {
            Intent appInfo = new Intent(this, article5Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma6) {
            Intent appInfo = new Intent(this, article6Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma7) {
            Intent appInfo = new Intent(this, article7Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma8) {
            Intent appInfo = new Intent(this, article8Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma9) {
            Intent appInfo = new Intent(this, article9Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma10) {
            Intent appInfo = new Intent(this, article10Activity.class);
            startActivity(appInfo);
        }else if (id == R.id.ma11) {
            Intent appInfo = new Intent(this, article11Activity.class);
            startActivity(appInfo);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
