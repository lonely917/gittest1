package com.example.littles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RegActivity extends Activity implements OnClickListener {
	
	private Button logButton;
	private Button regButton;
	private ImageView backButton;
	private ImageView okButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_reg);
	     
	     logButton = (Button)findViewById(R.id.reg_log_btn);
	     regButton = (Button)findViewById(R.id.reg_reg_btn);
	     backButton = (ImageView)findViewById(R.id.reg_back_btn);
	     okButton = (ImageView)findViewById(R.id.reg_ok_btn);
	     
	     logButton.setOnClickListener(this);
	     regButton.setOnClickListener(this);
	     backButton.setOnClickListener(this);
	     okButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		Intent intent = null;
		switch (v.getId()) {
		case R.id.reg_log_btn:
			intent = new Intent(RegActivity.this,LogActivity.class);
			break;
		case R.id.reg_reg_btn:
		{
			//提示注册成功
			Toast.makeText(RegActivity.this, "注册成功，请登录！", Toast.LENGTH_SHORT).show();
			intent = new Intent(RegActivity.this,LogActivity.class);
			break;
		}
		case R.id.reg_back_btn:
			intent = new Intent(RegActivity.this,LogAndRegActivity.class);
			break;
		case R.id.reg_ok_btn:
			intent = new Intent(RegActivity.this,HomeActivity.class);
			break;
		default:
			break;
		}
		
		if( intent != null )
		{
			RegActivity.this.finish();
			startActivity(intent);
		}
		
	}
}
