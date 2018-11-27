package app.hackathon.suki.suki.modules.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {
    private ActivityMainMenuBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);

        View toolbar = findViewById(R.id.layout_header);
        TextView textView = toolbar.findViewById(R.id.text_headerTitle);
        textView.setText(R.string.mainMenu);

        mBinding.buttonNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, VendorActivity.class );
                startActivity(intent);
            }
        });
        mBinding.buttonMyprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MyProductsActivity.class );
                startActivity(intent);
            }
        });
        mBinding.buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, StatisticsActivity.class );
                startActivity(intent);
            }
        });
        mBinding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class );
                startActivity(intent);
            }
        });

        mBinding.buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, ChatActivity.class );
                startActivity(intent);
            }
        });

    }
}
