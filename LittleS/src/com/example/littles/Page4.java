package com.example.littles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.support.v4.app.NavUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Page4 extends Activity {

	LinearLayout personalInfo_Layout;
	final int personalInfo_RequestCode = 1;
	ImageView headImageView;
	LinearLayout singn_Layout;
	TextView signTextView;
	final int sign_RequestCode = 2;
	LinearLayout about_Layout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        initdata();
        
        personalInfo_Layout.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Page4.this, SetPersonalInfoActivity.class);
				startActivityForResult(intent, personalInfo_RequestCode);
			}
		});
        
        singn_Layout.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Page4.this, SetSignActivity.class);
				startActivityForResult(intent, sign_RequestCode);
			}
		});
        
        about_Layout.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
			}
		});
    }

    private void initdata(){
		
		personalInfo_Layout = (LinearLayout)findViewById(R.id.setting_personInfo_layout);
		singn_Layout = (LinearLayout)findViewById(R.id.setting_sign_layout);
		about_Layout = (LinearLayout)findViewById(R.id.setting_about_layout);
		
		headImageView = (ImageView)findViewById(R.id.setting_head_imageview);
		signTextView = (TextView)findViewById(R.id.setting_sign_textview);
		
        
        Bitmap head;
		
	}

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode)
		{
		//¸ü»»ÃûÆ¬
		case personalInfo_RequestCode:
			if( resultCode == RESULT_OK )
			{
				Bitmap image = data.getParcelableExtra("img");
				if( image == null )
					 return;
				headImageView.setImageBitmap(image);
			}
			break;
		case sign_RequestCode:
			if( resultCode == RESULT_OK )
			{
				signTextView.setText(data.getStringExtra("sign"));
			}
				
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_page4, menu);
        return true;
    }

    
}
