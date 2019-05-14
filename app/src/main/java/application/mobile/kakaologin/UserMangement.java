package application.mobile.kakaologin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.squareup.picasso.Picasso;

public class UserMangement extends AppCompatActivity {
    private TextView username;
    private ImageView userPro;
    private Button btn_lgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mangement);
        username = findViewById(R.id.user_name);
        userPro = (ImageView) findViewById(R.id.userPro);
        btn_lgt = findViewById(R.id.btn_logout);
        Intent intent = getIntent();
        String name = (String) intent.getExtras().getString("name");
        String profileUrl = (String) intent.getExtras().getString("URL");

        username.setText(name);
        Picasso.with(this)
                .load(profileUrl)
                .fit()
                .into(userPro);


        //setUI(name, profileUrl);

        btn_lgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    protected void logout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Log.e("aaaa", "Sucess log out");
                Intent intent = new Intent(UserMangement.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void setUI(String name, String profileUrl){
        username.setText(name);
        Picasso.with(this)
                .load(profileUrl)
                .fit()
                .into(userPro);
    }
}
