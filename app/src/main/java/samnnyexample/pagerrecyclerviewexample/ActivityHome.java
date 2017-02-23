package samnnyexample.pagerrecyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity {


  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager recyclerLM;
  private Toolbar mToolbar;



  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    initToolBar();
    initRecyclerView();
  }

  public void initToolBar(){
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    // toolbar.setLogo(R.drawable.ic_launcher); //set custom logo here in the toolbar
    mToolbar.setTitle("PagerRecyclerViewExample");
    // Title must be set before the setSupportActionBar gets called, otherwise it wont work
    // toolbar.setSubtitle("sub title");
    setSupportActionBar(mToolbar);
    mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.gift_shop:
            example(null);
            break;
          case R.id.location:
            example(null);
            break;
        }
        return true;
      }
    });
  }

  public void initRecyclerView(){

    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    mRecyclerView.setHasFixedSize(true);
    recyclerLM = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(recyclerLM);
    mRecyclerView.setAdapter(new CustomerAdapter(this));
  }


  //inflating the menu into toolbar
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.home_menu, menu);
    return true;
  }


  public void example(View view){
    Toast.makeText(this, "redirect to account", Toast.LENGTH_SHORT).show();
  }








}
