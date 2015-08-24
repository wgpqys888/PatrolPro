package com.ab.health;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecordCourseActivity extends Activity {

	private String record;
	private EditText inputWeight;
	private float cal;
	int addCal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recordcourse);
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		cal = Float.valueOf(intent.getStringExtra("cal"));
	
		TextView titleTv = (TextView) findViewById(R.id.act_recordCal_name_tv);
		titleTv.setText(title);
		
		inputWeight = (EditText) findViewById(R.id.act_recordCal_amount_et);
		
		
		
		BtnClick btnClick =new BtnClick();
		Button btnBack = (Button) findViewById(R.id.act_recordCal_back_btn);
		btnBack.setOnClickListener(btnClick);
		
		TextView commit = (TextView) findViewById(R.id.act_recordCal_commit_btn);
		commit.setOnClickListener(btnClick);
		
	}
	
	class BtnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.act_recordCal_back_btn:
				finish();
				break;
			case R.id.act_recordCal_commit_btn:
				if(inputWeight.getText().toString().length() <= 0){
					Toast.makeText(getApplicationContext(), "请输入摄入量", Toast.LENGTH_SHORT).show();
					break;
				}
				String inputCal = inputWeight.getText().toString();
				int input = Integer.valueOf(inputCal);				
				float foodPerWeight =  cal / 100;				
				addCal = (int)(input * foodPerWeight);
				record = "您已经记录" + String.valueOf(addCal) + "千卡";
				Toast.makeText(getApplicationContext(), record, Toast.LENGTH_SHORT).show();
				db();
				finish();
				break;
			default:
				break;
				
				
			}
		}
		
	}
	
	private void db(){
		SQLiteDatabase db = openOrCreateDatabase("health", Context.MODE_PRIVATE, null);		
		Calendar now = Calendar.getInstance();
    	int year = now.get(Calendar.YEAR) - 2000;
    	int month = now.get(Calendar.MONTH) + 1;
    	int day = now.get(Calendar.DAY_OF_MONTH) ;
    	int hour = now.get(Calendar.HOUR_OF_DAY);
    	int minute = now.get(Calendar.MINUTE);
		ContentValues cv = new ContentValues();
		cv.put("year", year);
		cv.put("month", month);
		cv.put("day", day);
		cv.put("hour", hour);
		cv.put("minute", minute);
		cv.put("calorie", addCal);
		db.insert("record_course", null, cv);
		
	/*	Cursor c = db.rawQuery("SELECT * FROM person", null);
		while( c.moveToNext()){
			int _id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex("name"));
			Log.i("db", name);
		}*/
		
		db.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
