package project.trustuff.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class SignupTask extends AsyncTask<String, String, Void>
{
	
	Context context;
	EditText uname, pass1, pass2, email;
	ProgressDialog progressDialog;
	Intent intent;
    InputStream is = null ;
    String result = "";
	
	public SignupTask(Context context, EditText uname, EditText pass1, EditText pass2, EditText email)
	{
		this.context = context;
		progressDialog = new ProgressDialog(context);
		this.uname = uname;
		this.pass1 = pass1;
		this.pass2 = pass2;
		this.email = email;
		
		 intent = new Intent(context, Login.class);
	}
	
	
	 public void onPreExecute() {
	       
	        progressDialog.show();
	        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
	            @Override
	            public void onCancel(DialogInterface arg0) {
	            	SignupTask.this.cancel(true);
	            }
	        });
	    }
	 
	 
	    @Override
	    public Void doInBackground(String... params) {
	        String url_select = "http://www.trustuff.net/practice/insert.php";

	        HttpClient httpClient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(url_select);

	        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
	        param.add(new BasicNameValuePair("username", uname.getText().toString()));
	        param.add(new BasicNameValuePair("pass1", pass1.getText().toString()));
	        param.add(new BasicNameValuePair("pass2", pass2.getText().toString()));
	        param.add(new BasicNameValuePair("email", email.getText().toString()));

	        try {
	            httpPost.setEntity(new UrlEncodedFormEntity(param));

	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();

	            //read content
	            is =  httpEntity.getContent();

	        } catch (Exception e) {

	            Log.e("log_tag", "Error in http connection " + e.toString());
	            //Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
	        }
	       

	        return null;
	    }
	    public void onPostExecute(Void v)
	    {

	    	progressDialog.dismiss();
	    	Toast.makeText(context, "Great! now sign in and enjoy", Toast.LENGTH_LONG).show();

	    }
		

}
