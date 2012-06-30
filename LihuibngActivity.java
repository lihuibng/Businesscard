package lihuibng.android.homework1;

import java.io.File;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
public class LihuibngActivity extends TabActivity /*implements OnTabChangeListener*/{

	private TabHost myTabhost;
	private Button lgin = null;
	private Button register = null;
	private EditText edittext1 = null;
	private EditText edittext2 = null;
	Bundle state = null;
	ListView my_friends = null;
	private ImageButton imagebutton1 = null;
	Bitmap bi = null;
	ArrayAdapter<String> array_name = null;
	static public SQLiteDatabase database = null;
	
	EditText friend_name = null;
	EditText friend_phone = null;
	Button   friend_university = null;
	Button   friend_college = null;
	Button   friend_grade = null;
	Button   friend_home = null;
	EditText friend_QQ = null;
	EditText friend_email = null;
	Button friend_ok = null;
	Button friend_reset = null;
	
	String friend_name_string = null;
	String friend_phone_string = null;
	String friend_university_string = null;
	String friend_college_string = null;
	String friend_grade_string = null;
	String friend_home_string = null;
	String friend_QQ_string = null;
	String friend_email_string = null;
 
	
	private int location = 0; 
	private String locationname = null;
			@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		state = savedInstanceState;
		
		myTabhost=this.getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main, myTabhost.getTabContentView(), true);
		myTabhost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	
		myTabhost
				.addTab(myTabhost.newTabSpec("tab1")
						.setIndicator("Login",
								getResources().getDrawable(R.drawable.grass))
		                      .setContent(R.id.login1));
								
		myTabhost
				.addTab(myTabhost.newTabSpec("tab2")
						.setIndicator("Edit",
								getResources().getDrawable(R.drawable.grass))
						.setContent(R.id.editcard1));
		
		myTabhost
				.addTab(myTabhost.newTabSpec("tab3")
						.setIndicator("My Friends",
								getResources().getDrawable(R.drawable.grass))
						.setContent(R.id.name_of_friend));
	    String path = "/sdcard/myfriends.db";
		database = open_create_database(LihuibngActivity.this, path);
	    
		 //create_table(database);
	  	Toast.makeText(LihuibngActivity.this, "hha", 10).show();
		
		
		lgin = (Button)findViewById(R.id.lgin);
		register = (Button)findViewById(R.id.register);
		edittext1 = (EditText)findViewById(R.id.userid2);
		edittext2 = (EditText)findViewById(R.id.passwd2);
		
		
		
		//the second tab
				
		
		imagebutton1 = (ImageButton)findViewById(R.id.imagebutton);
        friend_name = (EditText)findViewById(R.id.name2);
		friend_phone = (EditText)findViewById(R.id.telphone2);
		friend_university = (Button)findViewById(R.id.university2);
		friend_college = (Button)findViewById(R.id.college2);
		friend_grade = (Button)findViewById(R.id.grade2);
		friend_home = (Button)findViewById(R.id.hometown2);
		friend_QQ = (EditText)findViewById(R.id.QQ2);
		friend_email = (EditText)findViewById(R.id.email2);
		
		friend_ok = (Button)findViewById(R.id.button1);
		friend_reset = (Button)findViewById(R.id.button2);
		
		
		my_friends = (ListView)findViewById(R.id.name_of_friend);
		
		imagebutton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Toast.makeText(LihuibngActivity.this, "welcome!", 1).show();	
				//
				new AlertDialog.Builder(LihuibngActivity.this).setTitle("Choose one of them ")
				.setIcon(R.drawable.grass).setSingleChoiceItems(R.array.capture_image, 0, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						location = which;
						String [] items = getResources().getStringArray(R.array.capture_image);
						locationname = items[location];
						
					}
				}).setPositiveButton(R.string.choose_button_OK, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(location == 0) {
						Toast.makeText(LihuibngActivity.this, "hahafsfs", 1).show();
					Intent	it = new Intent(LihuibngActivity.this, GalleryActivity.class);
					    it.putExtra("start", 1);
					    startActivityForResult(it, 1);
						
						}
						else if (location == 1) {
							Toast.makeText(LihuibngActivity.this, "hahaddfsf" + new Integer(location).toString() , 1).show();
						Intent	it1 = new Intent(LihuibngActivity.this, Camera_test.class);
						    it1.putExtra("start1", 2);
						    startActivityForResult(it1, 2);
							
						}
					    
					    
						
						
					}
				}).setNegativeButton(R.string.choose_button_close, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).show();
				
				
				
			     
				Toast.makeText(LihuibngActivity.this, "haha", 1).show();
						
			}
		});

				
					
		lgin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if( v.getId() == R.id.lgin && edittext1.getText().toString().equals("123") && edittext2.getText().toString().equals("123"))
				{
		    	AlertDialog dialog1 = new AlertDialog.Builder(LihuibngActivity.this).setIcon(R.drawable.gimp)
		    			.setTitle("Login").setMessage("Login successfully!" + edittext1.getText()).create();
		    	    dialog1.show();
		           		    	
				}
				else 
				{
					AlertDialog dialog1 = new AlertDialog.Builder(LihuibngActivity.this).setIcon(R.drawable.gimp)
			    			.setTitle("Login").setMessage("Login failed!" + edittext1.getText()).create();
			    	dialog1.show();

				}
			}
		});
		
      register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId() == R.id.button2) {
		    	AlertDialog dialog2 = new AlertDialog.Builder(LihuibngActivity.this).setIcon(R.drawable.gimp)
		    			.setTitle("Login").setMessage("Login unsuccessfully!").create();
		    	dialog2.show();
				}
			}
		});

      friend_ok.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
           friend_name_string = ((TextView)friend_name).getText().toString();
           friend_phone_string = friend_phone.getText().toString();
           friend_university_string = friend_university.getText().toString();
           friend_college_string = friend_college.getText().toString();
           friend_grade_string = friend_grade.getText().toString();
           friend_home_string = friend_home.getText().toString();
           friend_QQ_string = friend_QQ.getText().toString();
           friend_email_string = friend_email.getText().toString();
           if (friend_name_string != null && !friend_name_string.equals("")) {
              ContentValues values = new ContentValues();
              values.put("name", friend_name_string);
              values.put("phone", friend_phone_string);
              values.put("university", friend_university_string);
              values.put("college", friend_college_string);
              values.put("grade", friend_grade_string);
              values.put("home", friend_home_string);
              values.put("QQ_number", friend_QQ_string);
              values.put("email_address", friend_email_string);
        	   database.insert("my_friends", null, values);
        	   
        	   
           }
         Toast.makeText(LihuibngActivity.this, "nihaoma ", 1).show();
           array_name = new ArrayAdapter<String>(LihuibngActivity.this, android.R.layout.simple_list_item_1);
     		my_friends.setAdapter(array_name);
          get_name_from_database(database, array_name); 
          
         // database.close();
           
		}
	});
      
   // the third tab
      my_friends.setOnItemClickListener(new AdapterView.OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String temp_name = array_name.getItem(arg2);
			Intent it = new Intent(LihuibngActivity.this, Make_Call.class);
			it.putExtra("selected_name", temp_name);
			
			startActivityForResult(it, 6);
			
			
		}}
      );
      
    
      
  my_friends.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String temp_name = array_name.getItem(arg2).toString();
		Toast.makeText(LihuibngActivity.this, temp_name , 12).show();
		
	}
	
	

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(LihuibngActivity.this, "nothing selected!", 1000).show();
	}
	  
});
   		
   	   
      
}
     			
	protected void  get_name_from_database(SQLiteDatabase db, ArrayAdapter<String> array_name_friend )
	{
		
		String sql = "select name from my_friends";
		String name = "name";
		
		Cursor mycursor = db.rawQuery(sql, null);
         int column_number_name = mycursor.getColumnIndex("name");
		while(mycursor.moveToNext()) {
			String name_item = mycursor.getString(column_number_name);
			array_name_friend.add(name_item);
		}
	
	}
			
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) {
		case 1:
			 Bundle bundle = data.getExtras();
			 
			 Integer ite = new Integer(bundle.getString("imageview"));
			 Bitmap imageview = BitmapFactory.decodeResource(getResources(), ite.intValue());
			 bi = getScaleImg(imageview, 335, 400);
			 imagebutton1.setBackgroundDrawable(new BitmapDrawable(bi));
			 
			 break;
		case 2:
			Toast.makeText(LihuibngActivity.this, "sucess", 1);
			Bundle bundle1 = data.getExtras();
			String camera_file = bundle1.getString("image");
			File camera_ = new File(camera_file);
			Bitmap camera_drawable = BitmapFactory.decodeFile(camera_file);
			Toast.makeText(LihuibngActivity.this, camera_file, 1);
			//Bitmap camera_drawable = BitmapFactory.decodeFile(filename);
		     bi = getScaleImg(camera_drawable, 335, 400);
			imagebutton1.setBackgroundDrawable(new BitmapDrawable(bi));
			 
			
	   default: 
		   break;
		
		}
		super.onActivityResult(requestCode, resultCode, data);
		
	}	
	
	
    protected  static Bitmap  getScaleImg(Bitmap bm, int newWidth, int newHeight) {
	        // 图片源
	        // Bitmap bm = BitmapFactory.decodeStream(getResources()
	        // .openRawResource(id));
	        // 获得图片的宽高
	        int width = bm.getWidth();
	        int height = bm.getHeight();
	        // 设置想要的大小
	        int newWidth1 = newWidth;
	        int newHeight1 = newHeight;
	        // 计算缩放比例
	        float scaleWidth = ((float) newWidth1) / width;
	        float scaleHeight = ((float) newHeight1) / height;
	        // 取得想要缩放的matrix参数
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);
	        // 得到新的图片
	        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
	          true);
	        return newbm;

	       }
	   
	
    protected SQLiteDatabase open_create_database(Context context, String database_path) {
    	 SQLiteDatabase  db = context.openOrCreateDatabase(database_path, MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE, null);
    	return db;
    }

    protected void create_table(SQLiteDatabase db) {
    	String  sql= "Create table my_friends(friend_id integer PRIMARY KEY autoincrement, name text, phone text, university text, college text, grade text, home text, QQ_number text, email_address text)";
    	
         db.execSQL(sql);   	
    }

    
    
  /*  protected void onActivityResult1(int requestCode, int resultCode, Intent data)
    {
    	
    	
    }
    */

}	



