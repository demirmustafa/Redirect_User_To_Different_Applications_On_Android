package sendmailorsms.mustafa.com.sendmailorsms;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class MainActivity extends Activity {

    Button btnSendSMS,btnSendMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendSMS =  (Button)findViewById(R.id.btnSendSMS);
        btnSendMail = (Button)findViewById(R.id.btnSendMail);

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsNumber = "05394053430";
                String smsText = "Deneme mesajı";
                Uri uri = Uri.parse("smsto:"+smsNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body",smsText);

                /*intent i karşılayacak bir activity oldupunu doğrulayalım*/

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activitis = packageManager.queryIntentActivities(intent,0);

                boolean isIntentSafe = activitis.size() > 0;
                if (isIntentSafe){
                    startActivity(intent);
                    System.out.println("Intent güvende");
                }
                else{
                    System.out.println("Intent güvende değil");
                }

            }
        });

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String [] {"ya.mustafademir@yahoo.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Android");intent.putExtra(Intent.EXTRA_TEXT,"Kod dosyalarina nasil ulasabiliriz");

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent,1);

                boolean isIntentSafe = activities.size()>0;
                if(isIntentSafe){
                    startActivity(intent);
                }
                
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
