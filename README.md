# PatPat_Android

## 简介
PatPat是一个类似于TapTap的游戏社区，分为用户、游戏、论坛三大模块，包含用户注册、登录、信息管理、游戏推荐、排行、分类、查看论坛、发布帖子、消息通知、搜索等功能。

采用混合模式开发，兼备native app的优良用户体验，又有web app的跨平台优点。

## 主要技术栈

### 应用架构

-   mvvm
    -   databinding

### View

-   包含标签的滑动视图：TabLayout+ViewPager2
-   抽屉菜单菜单：DrawerLayout+NavigationView
-   底部导航栏：BottomNavigationView
-   列表：RecyclerView
-   图片处理：Glide

### ViewModel

-   LiveData
-   网络连接：okHttp
-   图片处理：Glide

## 功能介绍

### 用户模块

#### 未登录时状态 ：

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182123760.jpg" width=250px />

#### 登录界面：

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182129028.jpg" width=250px />

#### 登录后主界面：

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182129573.jpg" width=250px />

#### 个人中心

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182135327.jpg" width=250px />

#### 个人资料编辑

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182135132.jpg" width=250px />

#### 关注/粉丝列表

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182137231.jpg" width=250px />

### 游戏模块

#### 主界面

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182137133.jpeg" width=250px />

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182130928.jpg" width=250px />

#### 游戏分类：

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182132213.jpg" width=250px />

#### 类型详情：

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182132310.jpg" width=250px />

### 社区模块

#### 社区详情

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182139881.jpg" width=250px />

#### 发布帖子

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182152445.jpg" width=250px />

#### 关注的用户及社区的新动态

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182140580.jpg" width=250px />

#### 论坛列表

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182141748.jpg" width=250px />

#### 消息通知

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182141724.jpg" width=250px />

### 搜索

#### 搜索界面

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182201493.jpg" width=250px />

#### 搜索结果

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182201256.jpg" width=250px />

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182202381.jpg" width=250px />

<img src="https://gitee.com/lin_po_sheng/android-study/raw/master/202207182202669.jpg" width=250px />

```
PatPat_Android-master
├─ .gitignore
├─ .idea
│  ├─ .gitignore
│  ├─ .name
│  ├─ compiler.xml
│  ├─ gradle.xml
│  ├─ jarRepositories.xml
│  ├─ misc.xml
│  ├─ modules
│  │  └─ app
│  └─ vcs.xml
├─ app
│  ├─ .gitignore
│  ├─ debug
│  │  ├─ app-debug.apk
│  │  └─ output-metadata.json
│  ├─ proguard-rules.pro
│  └─ src
│     ├─ androidTest
│     │  └─ java
│     │     └─ com
│     │        └─ lbs
│     │           └─ patpat
│     │              └─ ExampleInstrumentedTest.java
│     ├─ main
│     │  ├─ AndroidManifest.xml
│     │  ├─ assets
│     │  │  └─ dist
│     │  │     ├─ css
│     │  │     │  └─ app.074b2801.css
│     │  │     ├─ favicon.ico
│     │  │     ├─ img
│     │  │     │  ├─ BackButton.1a3fcde3.png
│     │  │     │  ├─ icon.4a6b6007.png
│     │  │     │  ├─ Level1.44b14012.png
│     │  │     │  ├─ Level2.3bb2a1e8.png
│     │  │     │  ├─ Level3.2ee95f04.png
│     │  │     │  ├─ Level4.2a232184.png
│     │  │     │  ├─ Level5.a4a79db0.png
│     │  │     │  ├─ loading.6ec2faac.gif
│     │  │     │  ├─ Star.c24f3116.png
│     │  │     │  ├─ 二次元.ff91e1ed.png
│     │  │     │  ├─ 养成.08a6594f.png
│     │  │     │  ├─ 动作.38685b34.png
│     │  │     │  ├─ 卡牌.dcf80b6b.png
│     │  │     │  ├─ 多人联机.85602ecb.png
│     │  │     │  ├─ 收集.c698eb81.png
│     │  │     │  └─ 放置.992dfe41.png
│     │  │     ├─ index.html
│     │  │     └─ js
│     │  │        ├─ app.c2adb451.js
│     │  │        ├─ app.c2adb451.js.map
│     │  │        ├─ chunk-vendors.4d2f38f9.js
│     │  │        └─ chunk-vendors.4d2f38f9.js.map
│     │  ├─ java
│     │  │  └─ com
│     │  │     └─ lbs
│     │  │        └─ patpat
│     │  │           ├─ adapter
│     │  │           │  ├─ ForumListAdapter.java
│     │  │           │  ├─ JSBasic.java
│     │  │           │  ├─ JSGameType.java
│     │  │           │  ├─ JSInterface.java
│     │  │           │  ├─ JSPostDetail.java
│     │  │           │  ├─ JSPostList.java
│     │  │           │  ├─ PersonalAboutAdapter.java
│     │  │           │  └─ UserListAdapter.java
│     │  │           ├─ AdaptiveBackground.java
│     │  │           ├─ CollectActivity.java
│     │  │           ├─ FollowAndFansActivity.java
│     │  │           ├─ ForumActivity.java
│     │  │           ├─ fragment
│     │  │           │  ├─ ForumFragment.java
│     │  │           │  ├─ ListFragment.java
│     │  │           │  ├─ PersonalAboutFragment.java
│     │  │           │  ├─ PersonalPublishFragment
│     │  │           │  │  ├─ PersonalPublishFragment.java
│     │  │           │  │  └─ PersonalPublishViewModel.java
│     │  │           │  └─ WebViewFragment
│     │  │           │     ├─ CollapsingWebView.java
│     │  │           │     ├─ WebViewFragment.java
│     │  │           │     └─ WebViewViewModel.java
│     │  │           ├─ global
│     │  │           │  ├─ BackHandledFragment.java
│     │  │           │  ├─ BackHandledInterface.java
│     │  │           │  ├─ MyActivity.java
│     │  │           │  └─ MyApplication.java
│     │  │           ├─ ImagePreviewLoader.java
│     │  │           ├─ MainActivity.java
│     │  │           ├─ model
│     │  │           │  ├─ AppInfo.java
│     │  │           │  ├─ ForumDetailModel.java
│     │  │           │  ├─ ForumModel.java
│     │  │           │  ├─ PersonalModel.java
│     │  │           │  └─ UserModel.java
│     │  │           ├─ ModifyInfoActivity.java
│     │  │           ├─ PersonalActivity.java
│     │  │           ├─ PostWriteActivity.java
│     │  │           ├─ SearchActivity.java
│     │  │           ├─ ui
│     │  │           │  ├─ discovery
│     │  │           │  │  ├─ DiscoveryFragment.java
│     │  │           │  │  └─ DiscoveryViewModel.java
│     │  │           │  ├─ dynamic
│     │  │           │  │  ├─ DynamicFragment.java
│     │  │           │  │  └─ DynamicViewModel.java
│     │  │           │  ├─ home
│     │  │           │  │  ├─ HomeFragment.java
│     │  │           │  │  └─ HomeViewModel.java
│     │  │           │  ├─ login_register
│     │  │           │  │  ├─ LoginActivity.java
│     │  │           │  │  ├─ LoginDataSource.java
│     │  │           │  │  ├─ LoginedUser.java
│     │  │           │  │  ├─ LoginFormState.java
│     │  │           │  │  ├─ LoginResult.java
│     │  │           │  │  ├─ LoginViewModel.java
│     │  │           │  │  ├─ LoginViewModelFactory.java
│     │  │           │  │  ├─ ModifyPasswdActivity.java
│     │  │           │  │  ├─ RegisterActivity.java
│     │  │           │  │  ├─ UserDao.java
│     │  │           │  │  └─ UserDatabase.java
│     │  │           │  └─ message
│     │  │           │     ├─ MessageFragment.java
│     │  │           │     └─ MessageViewModel.java
│     │  │           ├─ UserViewInfo.java
│     │  │           ├─ viewmodel
│     │  │           │  ├─ FollowAndFanViewModel.java
│     │  │           │  ├─ ForumListViewModel.java
│     │  │           │  ├─ ForumViewModel.java
│     │  │           │  ├─ ListViewModel.java
│     │  │           │  ├─ MainViewModel.java
│     │  │           │  ├─ PersonalAboutViewModel.java
│     │  │           │  ├─ SearchViewModel.java
│     │  │           │  └─ UserViewModel.java
│     │  │           └─ webViewActivity.java
│     │  └─ res
│     │     ├─ drawable
│     │     │  ├─ active_button_off.png
│     │     │  ├─ active_button_on.png
│     │     │  ├─ backbutton.png
│     │     │  ├─ background_forum.png
│     │     │  ├─ button_back.png
│     │     │  ├─ button_search.xml
│     │     │  ├─ circle_image_background.xml
│     │     │  ├─ discover_button_off.png
│     │     │  ├─ discover_button_on.png
│     │     │  ├─ drawer_background.png
│     │     │  ├─ drawer_background_custom.xml
│     │     │  ├─ drawer_collect.png
│     │     │  ├─ drawer_dark_mode.png
│     │     │  ├─ drawer_follow.png
│     │     │  ├─ drawer_logout.png
│     │     │  ├─ drawer_person_page.png
│     │     │  ├─ drawer_setting.png
│     │     │  ├─ drawer_version.png
│     │     │  ├─ home_button_off.png
│     │     │  ├─ home_button_on.png
│     │     │  ├─ icon_default.png
│     │     │  ├─ icon_discover.xml
│     │     │  ├─ icon_dynamic.xml
│     │     │  ├─ icon_home.xml
│     │     │  ├─ icon_message.xml
│     │     │  ├─ ic_home_black_24dp.xml
│     │     │  ├─ ic_launcher_background.xml
│     │     │  ├─ ic_notifications_black_24dp.xml
│     │     │  ├─ img.png
│     │     │  ├─ login_button.png
│     │     │  ├─ message_button_off.png
│     │     │  ├─ message_button_on.png
│     │     │  ├─ patpat.png
│     │     │  ├─ search.png
│     │     │  ├─ selector_tab_text_color.xml
│     │     │  ├─ selector_tab_text_size.xml
│     │     │  ├─ shape_botton_follow.xml
│     │     │  ├─ shape_botton_unfollow.xml
│     │     │  ├─ shape_modify_personal_info.xml
│     │     │  ├─ shape_personal_about.xml
│     │     │  ├─ shape_post_write_publish.xml
│     │     │  ├─ shape_search.xml
│     │     │  └─ title.png
│     │     ├─ drawable-v24
│     │     │  └─ ic_launcher_foreground.xml
│     │     ├─ layout
│     │     │  ├─ activity_collect.xml
│     │     │  ├─ activity_follow_and_fans.xml
│     │     │  ├─ activity_forum.xml
│     │     │  ├─ activity_login.xml
│     │     │  ├─ activity_main.xml
│     │     │  ├─ activity_modify_info.xml
│     │     │  ├─ activity_modify_passwd.xml
│     │     │  ├─ activity_personal.xml
│     │     │  ├─ activity_post_write.xml
│     │     │  ├─ activity_register.xml
│     │     │  ├─ activity_search.xml
│     │     │  ├─ activity_web_view.xml
│     │     │  ├─ drawer_header.xml
│     │     │  ├─ fragment_discovery.xml
│     │     │  ├─ fragment_dynamic.xml
│     │     │  ├─ fragment_forum.xml
│     │     │  ├─ fragment_home.xml
│     │     │  ├─ fragment_list.xml
│     │     │  ├─ fragment_message.xml
│     │     │  ├─ fragment_personal_about.xml
│     │     │  ├─ fragment_personal_publish.xml
│     │     │  ├─ fragment_web_view.xml
│     │     │  ├─ item_grid_hot.xml
│     │     │  ├─ item_grid_hottest.xml
│     │     │  ├─ item_personal_about.xml
│     │     │  ├─ item_search_forum.xml
│     │     │  ├─ item_search_user.xml
│     │     │  ├─ toolbar_discover.xml
│     │     │  ├─ toolbar_discover_detail.xml
│     │     │  ├─ toolbar_dynamic.xml
│     │     │  ├─ toolbar_home_collapse.xml
│     │     │  ├─ toolbar_home_expand.xml
│     │     │  ├─ toolbar_home_expand_bottom.xml
│     │     │  ├─ toolbar_message.xml
│     │     │  └─ toolbar_search.xml
│     │     ├─ layout-w1240dp
│     │     │  └─ activity_login.xml
│     │     ├─ layout-w936dp
│     │     │  └─ activity_login.xml
│     │     ├─ menu
│     │     │  ├─ bottom_nav_menu.xml
│     │     │  └─ drawer_menu.xml
│     │     ├─ mipmap-anydpi-v26
│     │     │  ├─ ic_launcher.xml
│     │     │  └─ ic_launcher_round.xml
│     │     ├─ mipmap-hdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-mdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xxhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xxxhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ navigation
│     │     │  └─ mobile_navigation.xml
│     │     ├─ values
│     │     │  ├─ colors.xml
│     │     │  ├─ dimens.xml
│     │     │  ├─ strings.xml
│     │     │  └─ themes.xml
│     │     ├─ values-land
│     │     │  └─ dimens.xml
│     │     ├─ values-night
│     │     │  └─ themes.xml
│     │     ├─ values-v29
│     │     │  └─ themes.xml
│     │     ├─ values-w1240dp
│     │     │  └─ dimens.xml
│     │     └─ values-w600dp
│     │        └─ dimens.xml
│     └─ test
│        └─ java
│           └─ com
│              └─ lbs
│                 └─ patpat
│                    └─ ExampleUnitTest.java
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradle.properties
├─ gradlew
├─ gradlew.bat
└─ README.md

```