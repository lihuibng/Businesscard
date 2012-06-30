package lihuibng.android.homework1;

/*
 * Copyright (C) 2012  Android homework
 * Version 1.0 (the "License") 
 * Author:wuyuangui
 * communication:notmycupoftea@163.com
 */

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Camera_test extends Activity {
    /** Called when the activity is first created. */
	   Button capture = null;
	   private Uri fileUri = null;
	   String filename = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_camera);
        
        Bundle bundle12 = this.getIntent().getExtras();
        
        int capture_camera = bundle12.getInt("start1");
       Toast.makeText(this, new Integer(capture_camera).toString(), 1);    
 
        if (capture_camera == 2) {
        capture = (Button)findViewById(R.id.capture_camera);
        capture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				   
				  File patha1 = new File("/data/data/lihuibng.android.homework1/");
				    String filename = patha1.toString() + "/wuyuangui.jpg";
				   
			       File out = new File(filename);
			       try{
			       out.createNewFile();
			        
			       }catch (Exception e){}
			       
			        Uri uri = Uri.fromFile(out);   
			        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			     

					startActivityForResult(intent, 3);
					setResult(4, (new Intent()).putExtra("image", filename));
				  Camera_test.this.finish(); 
			}
		});
        
        
         
        }
   }
    
     
  
}
    