package com.lbs.patpat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;
import com.lbs.patpat.databinding.ActivityMainBinding;
import com.lbs.patpat.global.MyActivity;
import com.lbs.patpat.global.MyApplication;
import com.lbs.patpat.ui.login_register.LoginActivity;
import com.lbs.patpat.ui.login_register.LoginedUser;
import com.lbs.patpat.ui.login_register.ModifyPasswdActivity;
import com.lbs.patpat.ui.login_register.UserDao;
import com.lbs.patpat.viewmodel.UserViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends MyActivity {

    private static Boolean isLogin;
    private static UserDao userDao;
    private static String token, uid;
    private static LoginedUser loginedUser;
    private ActivityMainBinding binding;
    private ImageView icon;
    private ConstraintLayout backGround;
    private TextView intro, nickName;

    public static LoginedUser getLoginedUser() {
        return loginedUser;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String newToken) {
        token = newToken;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String newUid) {
        uid = newUid;
    }

    public static boolean getIsLogin() {
        return isLogin;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = MyApplication.getUserDatabase().userDao();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View headerView = binding.navDrawerMenu.getHeaderView(0);

        icon = headerView.findViewById(R.id.header_avatar);
        backGround = headerView.findViewById(R.id.header_background);
        intro = headerView.findViewById(R.id.header_follow_and_fans);
        nickName = headerView.findViewById(R.id.header_nickname);


        initBottomNav();
        initNavDrawerMenu();



        //登录用户ViewModel
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getLoginedUser().observe(this, new Observer<List<LoginedUser>>() {
            @Override
            public void onChanged(List<LoginedUser> loginedUsers) {

                Log.d("TEST", "用户数为：" + String.valueOf(loginedUsers.size()));
                if (loginedUsers.size() == 0) {     //未登录
                    intro.setVisibility(View.GONE);
                    nickName.setText("点击头像登录");
                    icon.setImageDrawable(getDrawable(R.drawable.icon_default));
                    backGround.setBackground(getDrawable(R.drawable.drawer_background_custom));
                    backGround.getBackground().setAlpha(230);
                    isLogin = false;
                } else {
                    loginedUser = loginedUsers.get(0).clone();
                    isLogin = true;
                    setToken(loginedUsers.get(0).token);
                    setUid(loginedUsers.get(0).getUid());
                    intro.setVisibility(View.VISIBLE);
                    if (loginedUsers.get(0).intro.equals("null"))
                        intro.setText("这个人很懒，什么都没有写");
                    else
                        intro.setText(loginedUsers.get(0).intro);

                    if (loginedUsers.get(0).nickname.equals("null"))
                        nickName.setText("暂无昵称");
                    else
                        nickName.setText(loginedUsers.get(0).nickname);

                    if (loginedUsers.get(0).avatar.equals("null")) {
                        icon.setImageDrawable(getDrawable(R.drawable.icon_default));
                    } else
                        Glide.with(MainActivity.this)
                                .load(getString(R.string.server_ip) + loginedUsers.get(0).avatar)
                                .skipMemoryCache(true)//跳过内存缓存
                                .diskCacheStrategy(DiskCacheStrategy.NONE)//不要在disk硬盘缓存
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(icon);

                    if (loginedUsers.get(0).background.equals("null")) {
                        backGround.setBackground(getDrawable(R.drawable.drawer_background_custom));
                        backGround.getBackground().setAlpha(230);
                    } else
                        Glide.with(MainActivity.this)
                                .load(getString(R.string.server_ip) + loginedUsers.get(0).background)
                                .skipMemoryCache(true)//跳过内存缓存
                                .diskCacheStrategy(DiskCacheStrategy.ALL)//不要在disk硬盘缓存.centerCrop()
                                .into(new AdaptiveBackground(backGround));
                }

            }

        });

        //适配问题
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainDrawerLayout, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                binding.container.setPadding(0, insets.getSystemWindowInsetTop(), 0, 0);
                return insets;
            }
        });

        //检验Token有效期
        checkLoginState();
    }

    //初始化底部栏
    private void initBottomNav() {
        binding.navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        //修复图片不显示原图
        binding.navView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_discovery, R.id.navigation_dynamic, R.id.navigation_messages)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    //初始化抽屉菜单
    private void initNavDrawerMenu() {
        //修复图片不显示原图

        icon.setBackground(getDrawable(R.drawable.icon_default));
        binding.navDrawerMenu.setItemIconTintList(null);
        binding.navDrawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_person_page:
                        startPersonalActivity();
                        break;
                    case R.id.drawer_follow:
                        startFollowAndFansActivity();
                        break;
                    case R.id.drawer_collect:
                        startCollectActivity();
                        break;
                    case R.id.drawer_version:
                        try {
                            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                            Toast.makeText(MainActivity.this, "当前版本：" + versionName, Toast.LENGTH_SHORT).show();
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
//                        new Thread(new Runnable() {   //修改Token
//                            @Override
//                            public void run() {
//                                LoginedUser user = MyApplication.getUserDatabase().userDao().getLoginUser().get(0);
//                                user.setToken("123456");
//                                MyApplication.getUserDatabase().userDao().updateUser(user);
//                            }
//                        }).start();
                        break;
                    case R.id.drawer_logout:
                        logOut();
                        break;
                    case R.id.drawer_dark_mode:
                        if(getIsLogin())
                            startActivity(new Intent(MainActivity.this, ModifyPasswdActivity.class));
                        else
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        //头部点击事件

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPersonalActivity();
            }
        });
    }

    //打开个人中心
    private void startPersonalActivity() {
        Intent intent;
        if (isLogin) {       //已登录，点击事件为打开个人中心
            intent = new Intent(MainActivity.this, PersonalActivity.class);
            intent.putExtra("uid", getUid());
        } else {      //未登录，点击事件为打开登录活动
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(intent);
    }

    //打开关注列表
    private void startFollowAndFansActivity() {
        Intent intent;
        if (isLogin) {       //已登录，点击事件为打开关注列表
            intent = new Intent(MainActivity.this, FollowAndFansActivity.class);
            intent.putExtra("uid", getUid());
        } else {      //未登录，点击事件为打开登录活动
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(intent);
    }

    private void startCollectActivity() {
        Intent intent;
        if (isLogin) {       //已登录，点击事件为打开收藏列表
            intent = new Intent(MainActivity.this, CollectActivity.class);
            intent.putExtra("uid", getUid());
        } else {      //未登录，点击事件为打开登录活动
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(intent);

    }

    //检验登录状态
    private void checkLoginState() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //Log.d(getString(R.string.log_tag), "行数为：" + String.valueOf(userDao.getCount()));
                if (userDao.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "用户未登录", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    try {
                        String cachedToken = userDao.getToken()[0];
                        String testTokenUrl = getString(R.string.server_ip) + "/user/test";
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .addHeader("token", cachedToken)
                                .post(new RequestBody() {
                                    @Override
                                    public MediaType contentType() {
                                        return null;
                                    }

                                    @Override
                                    public void writeTo(BufferedSink sink) throws IOException {

                                    }
                                })
                                .url(testTokenUrl)
                                .build();

                        Response response = null;
                        response = client.newCall(request).execute();
                        String responseBody = response.body().string();
                        //Log.d("TEST", "\ttestToken Body:" + responseBody);
                        if (responseBody.equals("请求成功")) {
                            String searchUrl = getString(R.string.server_ip) + "/user/" + userDao.getUID()[0];
                            client = new OkHttpClient();
                            request = new Request.Builder()
                                    .url(searchUrl)
                                    .build();


                            response = client.newCall(request).execute();
                            responseBody = response.body().string();
                            Log.d("TEST", "run: "+searchUrl);
                            JSONObject jsonObject = new JSONObject(responseBody);
                            JSONObject data = jsonObject.getJSONObject("data");
                            LoginedUser update = userDao.getLoginUser().get(0).clone();
                            update.setBackground(data.getString("background"));
                            update.setAvatar(data.getString("avatar"));
                            update.setIntro(data.getString("intro"));
                            update.setGender(data.getInt("gender"));
                            update.setNickname(data.getString("nickname"));
                            userDao.deleteUser();
                            userDao.insertUser(update);

                            Toast.makeText(getApplicationContext(), "用户已登录", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            Log.d("TEST", "用户已登录");

                        } else {
                            userDao.deleteUser();
                            Toast.makeText(getApplicationContext(), "登录已过期", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            Log.d("TEST", "登录已过期");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }
        }).start();
    }

    //退出登录
    private void logOut() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser();
                // 发送退出登录的GET请求
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .addHeader("token", getToken())
                        .url(getString(R.string.server_ip) + "/user/logout")
                        .build();
                try {
                    client.newCall(request).execute();
                    // 处理退出登录成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    // 处理异常
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "退出登录异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }
}