package project.trustuff.net;

import java.io.InputStream;
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
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class SellTask  extends AsyncTask<String, String, Void>{
	
	Context context;
	EditText title, description, price;
	String category, imgUrl, name;
	ProgressDialog progressDialog;
    InputStream is = null ;
    String result = "";
	
	public SellTask(Context context, EditText title, EditText description, String category, EditText price, String imgUrl, String name)
	{
		this.context = context;
		progressDialog = new ProgressDialog(context);
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imgUrl = imgUrl;
		this.name = name;
		
		 
	}
	
	
	 public void onPreExecute() {
	       
	        progressDialog.show();
	        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
	            @Override
	            public void onCancel(DialogInterface arg0) {
	            	SellTask.this.cancel(true);
	            	Toast.makeText(context, "Cancelled...", Toast.LENGTH_LONG).show();
	            }
	        });
	    }
	 
	 
	    @Override
	    public Void doInBackground(String... params) {
	        String url_select = "http://www.trustuff.net/practice/itemInsert.php";

	        HttpClient httpClient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(url_select);

	        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
	        param.add(new BasicNameValuePair("title", title.getText().toString()));
	        param.add(new BasicNameValuePair("description", description.getText().toString()));
	        param.add(new BasicNameValuePair("category", category));
	        param.add(new BasicNameValuePair("price", price.getText().toString()));
	        param.add(new BasicNameValuePair("imgUrl", imgUrl));
	        param.add(new BasicNameValuePair("name", name));
	        


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
	    	Toast.makeText(context, "Great! Your stuff was stuffed", Toast.LENGTH_LONG).show();

	    }

}
