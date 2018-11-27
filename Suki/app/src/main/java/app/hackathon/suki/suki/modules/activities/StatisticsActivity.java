package app.hackathon.suki.suki.modules.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivitySetupProductsBinding;
import app.hackathon.suki.suki.databinding.ActivityStatisticsBinding;

public class StatisticsActivity extends AppCompatActivity {
    private ActivityStatisticsBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);
        View toolbar = findViewById(R.id.layout_header);
        TextView textView = toolbar.findViewById(R.id.text_headerTitle);
        ImageView imageView = toolbar.findViewById(R.id.button_back);
        textView.setText(R.string.statistics);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
