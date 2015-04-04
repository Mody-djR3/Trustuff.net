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
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class LoginTask extends AsyncTask<String, String, Void>
{
	
	Context context;
	EditText uname, password;
	ProgressDialog progressDialog;
	Intent intent;
    InputStream is = null ;
    String result = "";
    String sUname;
	
	public LoginTask(Context context, EditText uname, EditText password)
	{
		this.context = context;
		progressDialog = new ProgressDialog(context);
		this.uname = uname;
		this.password = password;
		sUname = uname.getText().toString();
		
		intent = new Intent(context, MainActivity.class);
     	intent.putExtra("USER_NAME" ,sUname);

	}
	 

	

    public void onPreExecute() {
       
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
            	LoginTask.this.cancel(true);
            }
        });
    }
    @Override
    public Void doInBackground(String... params) {
        String url_select = "http://www.trustuff.net/practice/select.php";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url_select);

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

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
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line=br.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error converting result "+e.toString());
        }

        return null;

    }
    public void onPostExecute(Void v) {

        // ambil data dari Json database
        try {
            Log.e("log_tag", "1");
            JSONArray Jarray = new JSONArray(result);
            boolean access = false;
            for (int i = 0; i < Jarray.length(); i++) {
                Log.e("log_tag", "2");
                JSONObject Jasonobject = null;
                access = false;
                //text_1 = (TextView)findViewById(R.id.txt1);
                Jasonobject = Jarray.getJSONObject(i);
                Log.e("log_tag", "3");

                //get an output on the screen
                //String id = Jasonobject.getString("id");
                String username = Jasonobject.getString("username");
                String pass = Jasonobject.getString("pass1");

                if (uname.getText().toString().equalsIgnoreCase(username)
                        && password.getText().toString().equalsIgnoreCase(pass)) {
                    access = true;
                    break;
                } else {
                    access = false;
                }
                //text_1.append(id+"\t\t"+name+"\t\t"+password+"\t\t"+"\n");

            }

            if (access == true)
            {
                Toast.makeText(context, "Logged In", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
                
            } else
            {
                Toast.makeText(context, "please register", Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();

        } catch (Exception e)
        {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data "+e.toString());
        }
    }
}
