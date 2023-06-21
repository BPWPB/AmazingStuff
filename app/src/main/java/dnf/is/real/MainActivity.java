package dnf.is.real;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

//    private String showText;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 新建一个线程 让app内图片可显示出来的同时偷偷换屏幕图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在这里编写非UI线程的处理逻辑
//                setWallpaper();
                setLockScreenImage();

                // 如果要在此处使用Toast，请使用以下代码
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里编写在UI线程上下文中执行的逻辑，例如Toast
                        String showText = "SOMETHING HAS BEEN CHANGED, BE CAREFUL!";
                        Toast.makeText(getApplicationContext(), showText, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

    }

    private void setWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        String showText;
        try {
            // 获取将要设置的壁纸图片
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_image);

            // 设置壁纸
            wallpaperManager.setBitmap(bitmap);
            showText = "MOTHER FUCKER SURPRISE !!!";

//            Toast.makeText(getApplicationContext(), "MOTHER FUCKER SURPRISE !!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            showText = "something's going wrong contact with cyz";
//            Toast.makeText(getApplicationContext(), "something's going wrong contact with cyz", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLockScreenImage() {

        // 获取 WallpaperManager 对象
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        // 将 Drawable 转换为 Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zhiyiin);

        try {
            // 设置锁屏壁纸
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        // isFinishing()方法将返回true，说明用户正常返回到主屏幕，或者通过返回键调用了onBackPressed()方法退出了应用程序。
        if (isFinishing()) {
            // 用户正常返回到主屏幕，执行此处逻辑
            System.out.println("用户正常返回到主屏幕");
            Log.d("[*]", "用户正常返回到主屏幕");
        } else {
            System.out.println("用户使用了其他方式退出应用程序，例如按下Home键");
            Log.d("[*]", "用户使用了其他方式退出应用程序，例如按下Home键");
            // 用户使用了其他方式退出应用程序，例如按下Home键
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果应用程序从后台返回到前台，说明用户再次打开了应用程序
        System.out.println("应用程序从后台返回到前台");
        Log.d("[*]", "应用程序从后台返回到前台");
        if (flag==0) {
            flag = 1;
        } else {
            ImageView imageView = findViewById(R.id.background);
            imageView.setImageDrawable(null);
            imageView.setContentDescription("哈哈哈人傻了吧");

        }

    }

}