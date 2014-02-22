package com.example.littles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.support.v4.app.NavUtils;

public class SubForPage1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_for_page1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_for_page1, menu);
        return true;
    }
    
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(SubForPage1.this, Page1.class);
		// ��Activityת����һ��Window��Ȼ��ת����View
		Window w = Group1.group.getLocalActivityManager().startActivity("Page1Activity", intent);
		View view = w.getDecorView();
		// ����Ҫ��ת��Activity��ʾΪ��ActivityGroup������
		Group1.group.setContentView(view);
	} 
    
}
