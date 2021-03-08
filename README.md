#### **IQB_Student_App 简介：**

#### **注意：**
model_app目前是个空的Mock的业务module,开发人员根据model_app 复制出自己的业务module,并在壳子工程（model_app）的settings.gradle/app->build.gradle 文件中以及gradle.properties中加入对应的业务模块的编译配置；


**在app module 的build.gradle 文件中注释掉其它的module：**
```

/*********************模块是否参加编译逻辑******************************/
    if (!isModel.toBoolean()) {
//        implementation project(path: ':model_login')
//        implementation project(path: ':model_class')
        implementation project(path: ':model_patriarch')
        implementation project(path: ':model_users')
    }

```


### 新增加业务module
请参考model_app 

### 新增加View组件
参考lib_src

### rxjava+retrofit2
参考lib_api - net（包下）

### mvp + dagger
model ---DataManager(这个里面目前包含Http/SP封装，后续可能加入DB的封装)
V ---Activity/Fragment
P --- Presenter
M --- Model

dagger ---- IOC的工具，可以根据Scope 提供基于Component的单例.
1. Module 提供依赖的地方，主要用来提供一些第三方的依赖，因为没法为第三方的Class 构造中加入@Inject 参数（此项目表现为DataManager）
2. Component 是一个桥接，（Module 提供的实例 + 构造函数带有@Inject注释实例）------>Component----->(@Inject 注入实例的地方)
4. @Inject 在构造函数上时代表这个函数可以通过dagger 提供，同时构造函数有参数的时候参数也可以由dagger 提供，放在某个类的Field上的时候，代表这个Field 由dagger 提供。


#### 注意：
BaseActivity BaseFragment 这样的基类必须继承自lib_api 这个这个module 下的

### 模块间通信：
ARouter 阿里路由
************************************************************


### 资源文件命名规范：


#### 2.drawable/layout/strings/color/styles 的命名规则如下：

  **moduleName_TypeName_functionName**

  以contacts 模块为例：

  1.drawable

  ~~~
  contacts_shape_search_background.xml

  contacts_bg_action.png

  contacts_ic_default_avatar.jpg
  ~~~

  2.layout
  ~~~
  contacts_activity_add_new_friend.xml

  contacts_fragment_add_new_friend.xml

  contacts_item_recommend.xml
  ~~~

  3.String
  ~~~
 <string name="contacts_detail_title">通讯录</string>
  ~~~

  4.Style
  ~~~
      <style name="ContactsIndicatorTextBoldStyle">
          <item name="android:textSize">18sp</item>
          <item name="android:textStyle">bold</item>
      </style>
  ~~~



### 各个模块代码内容的基本原则：
**
1. 业务module(like model_app) 包含跟具体业务相关的代码
2. lib_api 用来添加第三方依赖并保留一些与业务相关的通用代码


### 屏幕适配方案
目前屏幕适配方案使用AndroidAutoSize 第三方库，这是一个封装完善的今日头条系方案：[AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize).宽高使用 360dp * 640dp,请开发人员按照UI设计图中的Android mdpi 标准尺寸开发.

### 目录介绍：
**  
├──FrameCode                                    项目总工程  
│   ├──lib_api                                 项目主体框架lib  
│   ├──lib_been                                项目实体类  
│   ├──lib_constants                           项目常量  
│   ├──lib_socket                              项目socket通信（长连接）  
│   ├──lib_player                              项目播放器、直播内核  
│   ├──lib_src                                 项目资源文件（存放UI、动画、适配方案、主题等）  
│   ├──model_app                               导航模块（空壳子、导航页）  
│   ├──model_home                              项目主页模块   
│   ├──model_login                             项目登录、注册模块   
│   ├──model_live                              项目直播模块  
│   ├──model_patriarch                         项目中心模块  
│   ├──model_user                              项目个人信息、设置模块

     
