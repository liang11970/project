package cn.com.projectdemos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.karics.library.zxing.android.CaptureActivity;
import com.karics.library.zxing.encode.CodeCreator;

/**
 * Created by wee on 16/6/20.
 */
public class ZXingDemo  extends AppCompatActivity {


    public static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    TextView qrCoded;
    ImageView qrCodeImage;
    Button creator, scanner;
    EditText qrCodeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        qrCoded = (TextView) findViewById(R.id.ECoder_title);
        qrCodeImage = (ImageView) findViewById(R.id.ECoder_image);
        creator = (Button) findViewById(R.id.ECoder_creator);
        scanner = (Button) findViewById(R.id.ECoder_scaning);
        qrCodeUrl = (EditText) findViewById(R.id.ECoder_input);

        creator.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = qrCodeUrl.getText().toString();
                try {
                    Bitmap bitmap = CodeCreator.createQRCode(url);
                    qrCodeImage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });

        scanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ZXingDemo.this,
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                qrCoded.setText("二维码内容： \n" + content);
                qrCodeImage.setImageBitmap(bitmap);
            }
        }
    }
}
