package com.example.littles;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class OrderActivity extends Activity {

	private Button submitButton;
	private Spinner mySpinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        setListeners();
    }

    private void initView() {
    	mySpinner = (Spinner) findViewById(R.id.myspinner);
    	ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, 
    			R.array.time_array, android.R.layout.simple_spinner_item);  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        mySpinner.setAdapter(adapter);
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_order, menu);
        return true;
    }

	public void OnClickSubmit(View v)
	{
		final Dialog dialog = new Dialog(this);	
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.payment);
		dialog.show();
		Button confirmButton = 	(Button) dialog.findViewById(R.id.buttonconfirm);	
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Ö§¸¶Íê³É", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
	}

    public void onClickForBtnReturn(View v)
    {
    	finish();
    }
}
