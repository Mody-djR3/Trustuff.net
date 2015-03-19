package project.trustuff.net;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		 Thread timer = new Thread(){

	            public void run(){
	                try{
	                    sleep(5000);
	                    Intent main = new Intent(WelcomeActivity.this, Login.class);
	                    startActivity(main);
	                }
	                catch (InterruptedException e){

	                }
	                finally{
	                    finish();
	                }
	            }
	        };

	        timer.start();

	}
	
	
    protected void onPause(){
        super.onPause();
    }


}
