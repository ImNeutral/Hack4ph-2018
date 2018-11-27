package app.hackathon.suki.suki.modules.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivitySetupAccountBinding;
import app.hackathon.suki.suki.databinding.ActivityUpdateAccountBinding;

public class UpdateAccountActivity extends AppCompatActivity {
    private ActivityUpdateAccountBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_account);

        View toolbar = findViewById(R.id.layout_header);
        TextView textView = toolbar.findViewById(R.id.text_headerTitle);
        ImageView imageView = toolbar.findViewById(R.id.button_back);
        textView.setText(R.string.updateAcc);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.linearPersonal.setVisibility(View.GONE);
                mBinding.linearBusiness.setVisibility(View.VISIBLE);
                mBinding.progressTwo.setBackground(getResources().getDrawable(R.drawable.bg_circular_filled));
                mBinding.progressTwo.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        mBinding.buttonNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateAccountActivity.this, SetUpProductsActivity.class );
                startActivity(intent);
                finish();
            }
        });

        mBinding.progressOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.linearPersonal.setVisibility(View.VISIBLE);
                mBinding.linearBusiness.setVisibility(View.GONE);
                mBinding.progressTwo.setBackground(getResources().getDrawable(R.drawable.bg_circular_clear));
                mBinding.progressTwo.setTextColor(Color.parseColor("#464646"));
            }
        });
    }
}
