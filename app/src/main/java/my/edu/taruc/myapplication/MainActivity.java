package my.edu.taruc.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_UPDATE_REQUEST = 5;
    public static final String PROFILE_EMAIL = "my.edu.taruc.myapplication.email";
    public static final String PROFILE_NAME =  "my.edu.taruc.myapplication.name";
    private TextView nameview , emailview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameview = (TextView)findViewById(R.id.nameview);
        emailview = (TextView)findViewById(R.id.emailview);
    }
    public void updateProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, PROFILE_UPDATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //REQUESTCODE = the unique request code that identify an intent call
        //RESULTCODE = result of an intent call; either OK or Cancel
        //daya = the actual data returned by an intent call
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PROFILE_UPDATE_REQUEST && resultCode ==RESULT_OK) {
            String name, email;
            email = data.getStringExtra(PROFILE_EMAIL);
            name = data.getStringExtra(PROFILE_NAME);
            nameview.setText(getString(R.string.name) + ":" + name);
            emailview.setText(getString(R.string.email) + ":" + email);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main Activity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main Activity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main Activity", "onPause");
    }

    public void visitBAIT2073(View v){
        String uri = "http://bait2073.000webhostapp.com/welcome/html";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);

    }
    public void showMain(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);

    }

    public void showDial(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0123456789"));
        startActivity(intent);

    }

    public void showSendTo(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"+"seekt@tarc.edu.my"));

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> activities = packManager.queryIntentActivities(intent , 0);
                boolean isInentSafe = activities.size() > 0;
        startActivity(intent);

    }
}
