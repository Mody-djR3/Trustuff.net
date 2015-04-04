package project.trustuff.net;

import java.io.ByteArrayOutputStream;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class Sell extends Fragment implements OnClickListener {
	
	private static final int SELECT_PICTURE = 1;
	ImageView uploadImg;
	View rootView;
	BootstrapButton sellSubmit;
	EditText title, description, price;
	String imgStr;
	String value;
	Context context;
	Bitmap bitmap;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    
         rootView = inflater.inflate(R.layout.sell, container, false);
         sellSubmit = (BootstrapButton) rootView.findViewById(R.id.submit);
         
         title = (EditText)  rootView.findViewById(R.id.title);
         description = (EditText)  rootView.findViewById(R.id.description);
         price = (EditText)  rootView.findViewById(R.id.price);
       
       uploadImg = (ImageView) rootView.findViewById(R.id.upImage);
       value = getActivity().getIntent().getStringExtra("USER_NAME");
        
        uploadImg.setOnClickListener(this);
        sellSubmit.setOnClickListener(this);
       
        return rootView;
    }
    
    
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()) {

            case R.id.upImage :
            
            	Intent pickIntent = new Intent();
            	pickIntent.setType("image/*");
            	pickIntent.setAction(Intent.ACTION_GET_CONTENT);

            	Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            	String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
            	Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
            	chooserIntent.putExtra
            	(
            	  Intent.EXTRA_INITIAL_INTENTS, 
            	  new Intent[] { takePhotoIntent }
            	);

            	startActivityForResult(chooserIntent, SELECT_PICTURE);

            		break;
            		
            case R.id.submit:
            	new SellTask(rootView.getContext(), title, description, "cars", price, imgStr, value).execute();
            	break;
          
        	}    
      }


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode)
		{
		case SELECT_PICTURE:
			if(resultCode == getActivity().RESULT_OK){
				

				Uri uri = data.getData();
				
				String[] projection ={ MediaStore.Images.Media.DATA };
				
				Cursor cursor = getActivity().getContentResolver().query(uri, projection, null,null,null);
				cursor.moveToFirst();
				
				int columnIndex= cursor.getColumnIndex(projection[0]);
				String filePath= cursor.getString(columnIndex);
				cursor.close();
				
				setPic(filePath, uploadImg);
				
			}
			break;
			
			default:
				break;
		}
	}
	
	
	private void setPic(String imagePath, ImageView destination) {
		
        

		
	    int targetW = destination.getWidth();
	    int targetH = destination.getHeight();
	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imagePath, bmOptions);
	   
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
	    
	    destination.setImageBitmap(bitmap);
        
        new Thread(new Runnable() {
            public void run() {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
                byte [] byte_arr = stream.toByteArray();
                imgStr= Base64.encodeToString(byte_arr, 1);
            }
        }).start();
	}
	

    
    

}
