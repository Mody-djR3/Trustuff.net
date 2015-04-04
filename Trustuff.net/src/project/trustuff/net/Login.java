package project.trustuff.net;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

public class Login extends ActionBarActivity implements OnClickListener {


	EditText uname, password, regName, regPass1, regPass2, regEmail;
	String sUname, sPass;
	String name, pass1, pass2, email;
	BootstrapButton btn_login, btn_signup;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		


		
		btn_login = (BootstrapButton) findViewById(R.id.submit);
        btn_signup = (BootstrapButton) findViewById(R.id.signup);
        
        uname = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        
        Drawable icon_uname = new IconDrawable(this, Iconify.IconValue.fa_user).colorRes(R.color.hint).actionBarSize();
        Drawable icon_pass = new IconDrawable(this, Iconify.IconValue.fa_lock).colorRes(R.color.hint).actionBarSize();
        uname.setCompoundDrawables(icon_uname, null, null, null);
        uname.setCompoundDrawablePadding(5);
        password.setCompoundDrawables(icon_pass, null, null, null);
        password.setCompoundDrawablePadding(5);

        regName = (EditText) findViewById(R.id.regUsername);
        regPass1 = (EditText) findViewById(R.id.regPassword1);
        regPass2 = (EditText) findViewById(R.id.regPassword2);
        regEmail = (EditText) findViewById(R.id.email);
        
        Drawable icon_regName = new IconDrawable(this, Iconify.IconValue.fa_user).colorRes(R.color.hint).actionBarSize();
        Drawable icon_regPass1 = new IconDrawable(this, Iconify.IconValue.fa_lock).colorRes(R.color.hint).actionBarSize();
        Drawable icon_regPass2 = new IconDrawable(this, Iconify.IconValue.fa_lock).colorRes(R.color.hint).actionBarSize();
        Drawable icon_regEmail = new IconDrawable(this, Iconify.IconValue.fa_envelope).colorRes(R.color.hint).actionBarSize();
        
        regName.setCompoundDrawables(icon_regName, null, null, null);
        regName.setCompoundDrawablePadding(5);
        
        regPass1.setCompoundDrawables(icon_regPass1, null, null, null);
        regPass1.setCompoundDrawablePadding(5);
        
        regPass2.setCompoundDrawables(icon_regPass2, null, null, null);
        regPass2.setCompoundDrawablePadding(5);
        
        regEmail.setCompoundDrawables(icon_regEmail, null, null, null);
        regEmail.setCompoundDrawablePadding(5);
        
        sUname = uname.getText().toString();
        sPass = password.getText().toString();
        
        name = regName.getText().toString();
        pass1 = regPass1.getText().toString();
        pass2 = regPass2.getText().toString();
        email = regEmail.getText().toString();
        
        
        

        
        
        TabHost tabH = (TabHost)findViewById(android.R.id.tabhost);
        tabH.setup();
       
        TabSpec ts = tabH.newTabSpec("tag 1");
        ts.setContent(R.id.tab1);
        ts.setIndicator("Login");
        
        tabH.addTab(ts);
        
        btn_login.setOnClickListener(this);
        
        ts = tabH.newTabSpec("tag 1");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Sign up");
        tabH.addTab(ts);
        
        btn_signup.setOnClickListener(this);
	}


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()) {

            case R.id.submit :
            	if(sUname != null && sPass != null)
            	{
            	new LoginTask(Login.this, uname, password).execute(); 
            	break;
            	}else
            	{
            		Toast.makeText(getApplication(), "Can't be empty", Toast.LENGTH_LONG).show();
            		break;
            	}
            case R.id.signup : 
            	if(name != null && pass1 != null && pass2 != null && email != null)
            	{
            	new SignupTask(Login.this, regName, regPass1, regPass2, regEmail).execute();break;
            	}else
            	{
            		Toast.makeText(getApplication(), "Can't be empty", Toast.LENGTH_LONG).show();
            		break;
            	}
            
        	}    
      }
        
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
