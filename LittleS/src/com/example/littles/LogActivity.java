package com.example.littles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class LogActivity extends Activity implements OnClickListener{
	
	private Button logButton;
	private Button forgetpwButton;
	private Button regButton;
	private ImageView backButton;
	private ImageView okButton;
	
	private static Handler handler;
	
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_log);
	     
	     handler = new Handler();
	     
	     logButton = (Button)findViewById(R.id.log_log_btn);
	     forgetpwButton = (Button)findViewById(R.id.log_forgetpw_btn);
	     regButton = (Button)findViewById(R.id.log_reg_btn);
	     backButton = (ImageView)findViewById(R.id.log_back_btn);
	     okButton = (ImageView)findViewById(R.id.log_ok_btn);
	     
	     logButton.setOnClickListener(this);
	     forgetpwButton.setOnClickListener(this);
	     regButton.setOnClickListener(this);
	     backButton.setOnClickListener(this);
	     okButton.setOnClickListener(this);
	     
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.log_log_btn:
		{
			LogLogic();
			intent = new Intent(LogActivity.this,HomeActivity.class);
			break;
		}
		case R.id.log_forgetpw_btn:
			intent = new Intent(LogActivity.this,HomeActivity.class);
			break;
		case R.id.log_reg_btn:
			intent = new Intent(LogActivity.this,RegActivity.class);
			break;
		case R.id.log_back_btn:
			intent = new Intent(LogActivity.this,LogAndRegActivity.class);
			break;
		case R.id.log_ok_btn:
			intent = new Intent(LogActivity.this,HomeActivity.class);
			break;
		default:
			break;
		}
		
		if( intent != null )
		{
			LogActivity.this.finish();
			startActivity(intent);
		}
	}
	
	private void LogLogic()
	{
		ProgressDialog waitingDlg;
		waitingDlg = new ProgressDialog(LogActivity.this);
		waitingDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		waitingDlg.setTitle("登录");
		waitingDlg.setIcon(R.drawable.ic_launcher);
		waitingDlg.setMessage("登录中,请稍后...");
		waitingDlg.setIndeterminate(false);//设置进度条是否为不明确  
		waitingDlg.setCancelable(true);//设置进度条是否可以按退回键取消 
		
		waitingDlg.setButton("取消", new DialogInterface.OnClickListener(){ 
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel(); 
             }
         });
		waitingDlg.show();
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		//waitingDlg.dismiss();
		//new LogThread(waitingDlg).start();
	}
	
	class LogThread extends Thread{
		
		ProgressDialog dlg;
		
		public LogThread( ProgressDialog dlg ){
			this.dlg = dlg;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					dlg.dismiss();
				}
			});
			
			
		}
	}
	
}
