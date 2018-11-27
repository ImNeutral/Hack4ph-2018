package app.hackathon.suki.suki.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.hackathon.suki.suki.R;


public class CustomInput extends LinearLayout {
    // Constants
    private static final String TAG = CustomInput.class.getSimpleName();

    // Variables
    private String mHeaderText;
    private String mEditTextHint;
    private String mEditTextText;
    private int mInputType;
    private int mMaxLength;
    private boolean mEnabled;


    // Views
    private TextView mHeaderTextView;
    private LinearLayout mInputLinearLayout;
    private LinearLayout mInputLinearBG;
    private EditText mInputEditText;
    private ImageView imageView;

    public CustomInput(Context context) {
        super(context);
        init(context);
    }

    public CustomInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(attrs);
        init(context);
    }

    public CustomInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_custom_input, this);
    }

    private void readAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                    R.styleable.CustomInput,
                0, 0);

        try {
            mHeaderText = a.getString(R.styleable.CustomInput_ci_header_text);
            if (mHeaderText == null) {
                mHeaderText = "";
            }

            mInputType = a.getInt(R.styleable.CustomInput_android_inputType, EditorInfo.TYPE_CLASS_TEXT);

            mEditTextHint = a.getString(R.styleable.CustomInput_ci_editText_input_hint);
            if (mEditTextHint == null) {
                mEditTextHint = "";
            }
            mEditTextText = a.getString(R.styleable.CustomInput_ci_editText_input_text);

            mMaxLength = a.getInt(R.styleable.CustomInput_android_maxLength, 100);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Initialize views
        mHeaderTextView = findViewById(R.id.text_itemHeader);
        mInputEditText = findViewById(R.id.editText_itemContent);
        mInputLinearLayout = findViewById(R.id.linearLayout_input);
        mInputLinearBG = findViewById(R.id.linearBG);

        if (!mHeaderText.isEmpty()) {
            mHeaderTextView.setVisibility(VISIBLE);
            mHeaderTextView.setText(mHeaderText);
        } else {
            mHeaderTextView.setVisibility(GONE);
        }


        mInputEditText.setHint(mEditTextHint);
        mInputEditText.setText(mEditTextText);
        mInputEditText.setInputType(mInputType);
        if (mMaxLength > 0) {
            mInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
        }

        mInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                setSelector(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        mInputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mInputLinearLayout.setPressed(true);
                    mHeaderTextView.setTextColor(Color.parseColor("#68C49F"));
                    mInputLinearBG.setBackground(getResources().getDrawable(R.drawable.bg_button_corner_active));
                } else {
                    mInputLinearLayout.setPressed(false);
                    mInputLinearLayout.setPressed(true);
                    mHeaderTextView.setTextColor(Color.parseColor("#4a4a4a"));
                    mInputLinearBG.setBackground(getResources().getDrawable(R.drawable.bg_button_corner));
                }
            }
        });

    }

    /**
     * Returns the string value of the EditText
     *
     * @return String content of EditText
     */
    public String getInputString() {
        if (mInputEditText != null) {
            return mInputEditText.getText().toString();
        }

        return "";
    }

    public void setEditTextText(String editTextText) {
        mInputEditText.setText(editTextText);
    }
    public boolean isEmpty(){
        if (mInputEditText ==null){
            return true;
        }
        return false;
    }

//    public void setSelector(@Nullable String errorMessage) {
//        mInputLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_text_box));
//    }

//    public void setError(@Nullable String errorMessage) {
//        if (errorMessage != null && !errorMessage.isEmpty()) {
//            mHasError = true;
//            mInputLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_text_box_error));
//        } else {
//            mHasError = false;
//            mInputLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_text_box));
//        }
//    }

    public EditText getInputEditText() {
        return mInputEditText;
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        //set progress wheel width and height based on parent height
//        int size = h - getPaddingTop() - getPaddingBottom();
//        ViewGroup.LayoutParams params = progressWheel.getLayoutParams();
//        params.width = size;
//        params.height = size;
//        progressWheel.setLayoutParams(params);
//    }

    /**
     * Sets the click action of action button
     *
     * @param l click listener to be used by the action button
     */

}