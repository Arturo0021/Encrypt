package notification.mx.encrypt;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main_Activity extends AppCompatActivity {

    byte[] algo;
    byte[] decrypt;
    TextView jamon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        jamon = (TextView) findViewById(R.id.jamon);

        try {
            byte[] keyStart = "jamon".getBytes();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr); // 192 and 256 bits may not be available
            SecretKey skey = kgen.generateKey();
            byte[] key = skey.getEncoded();

            algo  = Encryptor.encrypt(key, "Texto a Encriptar");

            decrypt = Encryptor.decrypt(key, algo);

            String str = new String(algo, "UTF-8");
            String str2 = new String(decrypt, "UTF-8");

            jamon.setText(str + " \n-\n " + str2);

        } catch (Exception e) {
            e.getMessage();
        }





    }
}
