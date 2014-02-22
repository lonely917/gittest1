package com.example.littles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SetSignActivity extends Activity{
	
	TextView signTextView;
	Button affirmBtn;
	static boolean affirm;
	static boolean saveSuc;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sign);
        
        affirm = false;
        saveSuc = false;
        signTextView = (TextView)findViewById(R.id.setting_sign_edittext);
        
        affirmBtn = (Button)findViewById(R.id.setting_sign_save_btn);
        affirmBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if( !signTextView.getText().toString().equals("") )
				{
					affirm = true;
					saveSign(signTextView.getText().toString());
				}
				else
				{
					Toast.makeText(SetSignActivity.this, "没有内容哦,亲", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
	}
	
	private void saveSign( String sign ){
		new saveSignTask(sign).execute("");
	}
	
	//返回键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent data = new Intent();
			if( affirm )
			{
				if( saveSuc )
				{
					setResult(RESULT_OK, data);
					data.putExtra("sign", signTextView.getText().toString());
				}
				else
				{
					setResult(RESULT_CANCELED);
				}
			}
			else
				setResult(RESULT_CANCELED);
			SetSignActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	class saveSignTask extends AsyncTask{

		ProgressDialog saveDialog;
		String hintString;
		boolean ret = false;
		String sign;
		
		public saveSignTask( String sign ){
			this.sign = sign;
		}
		protected void onPreExecute() {
			
			/*
			saveDialog = new ProgressDialog(SetSignActivity.this);
			saveDialog.setTitle("保存签名");
			saveDialog.setIcon(android.R.drawable.ic_dialog_info);
			saveDialog.setMessage("正在保存签名...");
			saveDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			saveDialog.setCancelable(true);
			saveDialog.show();
			*/
		}
		@Override
		protected Object doInBackground(Object... params) {
			
			ret = true;
			if( ret )
			{
				hintString = "保存成功";
			}
			else
				hintString = "保存失败";
			return null;
		}
		
		protected void onPostExecute(Object result) {
			saveSuc = true;
			Toast.makeText(SetSignActivity.this, hintString, Toast.LENGTH_SHORT).show();
			//saveDialog.dismiss();
			return ;
		}
		
	}
    public void onClickForBtnReturn(View v)
    {
    	finish();
    }
}
