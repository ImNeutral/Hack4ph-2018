package app.hackathon.suki.suki.modules.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivitySetupProductsBinding;
import app.hackathon.suki.suki.databinding.ActivitySplashScreenBinding;

public class SetUpProductsActivity extends AppCompatActivity {
    private ActivitySetupProductsBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setup_products);
        View toolbar = findViewById(R.id.layout_header);
        TextView textView = toolbar.findViewById(R.id.text_headerTitle);
        textView.setText(R.string.setupAcc);

    }
}
