package com.example.littles;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogAndRegActivity extends Activity implements OnClickListener{
	
	private Button logButton;
	private Button regButton;
	private Button aroudButton;
	
	
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_logandreg);
		 
	     logButton = (Button)findViewById(R.id.logreg_Log_btn);
	     regButton = (Button)findViewById(R.id.logreg_Reg_btn);
	     aroudButton = (Button)findViewById(R.id.logreg_Around_btn);
	     logButton.setOnClickListener(this);
	     regButton.setOnClickListener(this);
	     aroudButton.setOnClickListener(this);
	     
	 }
	 
	 @Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.logreg_Log_btn:
			intent = new Intent(LogAndRegActivity.this,LogActivity.class );
			break;
		case R.id.logreg_Reg_btn:
			intent = new Intent(LogAndRegActivity.this,RegActivity.class );
			break;
		case R.id.logreg_Around_btn:
			intent = new Intent(LogAndRegActivity.this,HomeActivity.class);
		default:
			break;
		}
		if( intent != null )
		{
			LogAndRegActivity.this.finish();
			startActivity(intent);
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		}
		
	}

}
