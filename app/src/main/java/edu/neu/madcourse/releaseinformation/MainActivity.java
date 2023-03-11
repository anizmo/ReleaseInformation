package edu.neu.madcourse.releaseinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * This application displays the signature type and the build target of the application.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize text view with findViewById
        TextView signatureTextView = findViewById(R.id.signature);

        // Initialize text view with findViewById
        TextView buildTargetTextView = findViewById(R.id.buildTarget);

        // Set the current signature of the app to display in the textView
        signatureTextView.setText("The Signature of the app is:\n" + getSignature());

        // Set the build target of the app to display in the textView
        buildTargetTextView.setText("The build target is:\n" + getBuildTarget());

    }

    /**
     * This method provides the signature type, try running this project on a phone/emulator from
     * the Android Studio and it should say "Debug Signature", generate a release APK and install it
     * on a phone/emulator and it should say "Release Signature".
     *
     * @return signature type of the current app
     */
    private String getSignature() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            Signature currentSignature = signatures[0];
            String signatureString = currentSignature.toCharsString();
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                Log.d("Signature", "Debug signature detected: " + signatureString);
                return "Debug Signature";
            } else {
                Log.d("Signature", "Release signature detected: " + signatureString);
                return "Release Signature";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Unable to find Signature";
        }
    }

    /**
     * This method provides the Build Target of the app, that is, it shows the Android OS Version
     * amd the SDK Version of the phone/emulator on which the application is installed. Install this
     * on different devices to see different Android OS Version numbers and SDK Numbers.
     *
     * @return build target of the current app
     */
    private String getBuildTarget() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Android OS Version: ").append(Build.VERSION.RELEASE).append("\n");
        stringBuilder.append("Android SDK Version: ").append(Build.VERSION.SDK_INT).append("\n");
        return stringBuilder.toString();
    }

}