package com.example.littles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ShareActivity extends Activity {

	private Button submitButton, reButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        initview();
        addListeners();
    }

    private void addListeners() {
//		submitButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), "分享成功", Toast.LENGTH_LONG).show();
//			}
//		});
//		
//		reButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ShareActivity.this.finish();
//			}
//		});
	}

	private void initview() {
//		submitButton = (Button) findViewById(R.id.submit_button);
//		reButton = (Button) findViewById(R.id.return_button);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_share, menu);
        return true;
    }

    public void onClickForBtnReturn(View v)
    {
    	finish();
    }    
    
    public void onClickForBtnShare(View v)
    {
		//关闭软键盘
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		
    	Toast toast = Toast.makeText(getApplicationContext(), "成功分享至新浪微博", Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.CENTER, 0, 0);
    	toast.show();
    }
}
