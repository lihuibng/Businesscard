package lihuibng.android.homework1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
//import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryActivity extends Activity {
	Button btn = null;
	Button btn1 = null;
	Button btn2 = null;
	Bundle state = null;
	Integer seg = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		state  = savedInstanceState;
        Bundle bundle = this.getIntent().getExtras();
        
        int imagecon = bundle.getInt("start");
        
        if (imagecon == 1) {
	
				// TODO Auto-generated method stub
				
				btn1 =(Button) findViewById(R.id.button3);
				btn2 = (Button)findViewById(R.id.button4);
				Gallery g = (Gallery) findViewById(R.id.gallery);
				
				final ImageAdapter imageadapter = new ImageAdapter(GalleryActivity.this);
				g.setAdapter(imageadapter);
				g.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View v, int position,
							long id) {
						//Bitmap camera_drawable = BitmapFactory.decodeFile(imageadapter.getitems(position));
						
						Bitmap imageview = BitmapFactory.decodeResource(getResources(), imageadapter.getitems(position));
					   Bitmap 	 bi = getScaleImg(imageview, 180, 270);
						 
						AlertDialog dialog2 = new AlertDialog.Builder(GalleryActivity.this).setIcon(new BitmapDrawable(bi))
				    			.setTitle("view").setMessage("please choose one !" + new Integer(position).toString()).create();
				    	dialog2.show();
				    	seg = imageadapter.getitems(position);

						
				 
					}

									});
                
				btn1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						 
						setResult(4, new Intent().putExtra("imageview", seg.toString()));
						GalleryActivity.this.finish();
					}
				});
				
				btn2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						 
						
					}
				});
				
				registerForContextMenu(g);		
			}
		
		
	}
	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(R.string.gallerytext);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
	 
		return true;
	}

	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;

		public ImageAdapter(Context c) {
			mContext = c;
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			mGalleryItemBackground = a.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 0);
			a.recycle();
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			imageView = new ImageView(mContext);
			imageView.setImageResource(mImageIds[position]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(200, 120));
			imageView.setBackgroundResource(mGalleryItemBackground);
			return imageView;
		}
		public Integer getitems(int position)
		{
			return mImageIds[position];
			
		}

		private Context mContext;

		private Integer[] mImageIds = { R.drawable.lover1,
				R.drawable.lover2, R.drawable.lover3, R.drawable.lover4,
				R.drawable.lover5, R.drawable.lover6, R.drawable.lover7,
				R.drawable.lover8, R.drawable.grass };
	}
	
	 protected  static Bitmap  getScaleImg(Bitmap bm, int newWidth, int newHeight) {
	       
		    int width = bm.getWidth();
	        int height = bm.getHeight();
	        
	        int newWidth1 = newWidth;
	        int newHeight1 = newHeight;
	        
	        float scaleWidth = ((float) newWidth1) / width;
	        float scaleHeight = ((float) newHeight1) / height;
	         
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);
	       
	        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
	          true);
	        return newbm;

	       }
	
}