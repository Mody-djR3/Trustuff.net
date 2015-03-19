package project.trustuff.net;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;


public class Sell extends Fragment implements OnClickListener {
	
	private static final int SELECT_PICTURE = 1;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	
        View rootView = inflater.inflate(R.layout.sell, container, false);
       
       
        BootstrapThumbnail uploadImg = (BootstrapThumbnail) rootView.findViewById(R.id.upImage);
        
        uploadImg.setOnClickListener(this);
        return rootView;
    }
    
    
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()) {

            case R.id.upImage :
            	


            	// ... 

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
            case R.id.signup : 
          
        	}    
      }

}
