package lihuibng.android.homework1;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Make_Call extends Activity {
	private static final String tel = "tel:";
	TextView college_view = null;
	  TextView email_view = null;
	  TextView grade_view = null;
	  TextView home_view = null;
	  Button make_call = null;
	  TextView name_view = null;
	  TextView phone_view = null;
	  TextView qq_view = null;
	  TextView university_view = null;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.make_call);
	        
	          name_view = (TextView)findViewById(R.id.name_view);
	          phone_view = (TextView)findViewById(R.id.phone_view);
	       university_view = (TextView)findViewById(R.id.university_view);
	         college_view = (TextView)findViewById(R.id.college_view);
	         grade_view = (TextView)findViewById(R.id.grade_view);
	          home_view = (TextView)findViewById(R.id.home_view);
	          qq_view = (TextView)findViewById(R.id.qq_view);
	          email_view = (TextView)findViewById(R.id.email_view);
	           make_call = (Button)findViewById(R.id.call);
	        
	        
	        Bundle bundle12 = this.getIntent().getExtras();
	        String [] select_name = new String[1];
	        	 select_name[0]	= bundle12.getString("selected_name");
	        	 File patha = Environment.getExternalStoragePublicDirectory(
	                     Environment.DIRECTORY_DOWNLOADS);
	     	    String path = patha.toString() + "/myfriends.db";
	        	    SQLiteDatabase databases = this.open_create_databases(Make_Call.this, path);
	        	    String sql= "select name, phone, university, college, grade, home, QQ_number, email_address from my_friends where name = ?";
	                 Cursor cursor = databases.rawQuery(sql, select_name);
	                 int name_col = cursor.getColumnIndex("name");
	                 int phone_col = cursor.getColumnIndex("phone");
	                 int university_col = cursor.getColumnIndex("university");
	                 int college_col = cursor.getColumnIndex("college");
	                 int grade_col = cursor.getColumnIndex("grade");
	                 int home_col = cursor.getColumnIndex("home");
	                 int qq_number_col = cursor.getColumnIndex("QQ_number");
	                 int email_address_col = cursor.getColumnIndex("email_address");
	               
	                 while(cursor.moveToNext())
	                 {
	                    name_view.setText(cursor.getString(name_col));
	                    phone_view.setText(cursor.getString(phone_col));
	                    university_view.setText(cursor.getString(university_col));
	                    college_view.setText(cursor.getString(college_col));
	                    grade_view.setText(cursor.getString(grade_col));
	                    home_view.setText(cursor.getString(home_col));
	                    qq_view.setText(cursor.getString(qq_number_col));
	                    email_view.setText(cursor.getString(email_address_col));
	                 }
	 
	                 make_call.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String phone = phone_view.getText().toString();
							Uri uri = Uri.parse(tel + phone);
							Intent it = new Intent(Intent.ACTION_CALL, uri);
							Make_Call.this.startActivity(it);
						}
					});
	 }
	 protected SQLiteDatabase open_create_databases(Context context, String database_path) {
    	 SQLiteDatabase  db = context.openOrCreateDatabase(database_path, MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE, null);
    	return db;
    }
	

}
