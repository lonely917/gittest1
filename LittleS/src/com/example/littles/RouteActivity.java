package com.example.littles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class RouteActivity extends Activity {
	
	private ImageView route1Image,route2Image,route3Image;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        initView();
        setListeners();
    }

    private void initView() {
		route1Image = (ImageView) findViewById(R.id.imgroute1);
		route2Image = (ImageView) findViewById(R.id.imgroute2);
		route3Image = (ImageView) findViewById(R.id.imgroute3);
	}

	private void setListeners() {
		route1Image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RouteActivity.this, TransportActivity.class);
//				intent.putExtra("com.example.littles.TYPE", 1);
				Bundle bundle = new Bundle();
				bundle.putInt("ARGS", 1);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		route2Image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RouteActivity.this, TransportActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("ARGS", 2);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		route3Image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RouteActivity.this, TransportActivity.class);
//				intent.putExtra("com.example.littles.TYPE", 1);
				Bundle bundle = new Bundle();
				bundle.putInt("ARGS", 3);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_route, menu);
        return true;
    }

    public void onClickForBtnReturn(View v)
    {
    	finish();
    }
}
