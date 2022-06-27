# IGitHub
github api client for android

## 1.使用到的github API
请求用户的 GitHub 身份
https://github.com/login/oauth/authorize

搜索github仓库
https://github.com/search/repositories

获取某个用户信息
url：https://api.github.com/users/{username}

## 2.实现的页面
登录页RegisterAndLoginActivity
主页MainActivity
搜索页（搜索接口）RepositoryFragment
个人资料页PersonalCenterActivity

## 3.用户登录状态持久化，可退出
     通用的错误页面layout_error
     动态权限申请对话框

## 4.使用kotlin语言编写，Jetpack技术栈（MVVM：DataBinding、Lifecycle、LiveData、ViewModel）
     流行第三方库：GlideV4、Retrofit2.6、OkHttp3、MMKV等

## 5.设计模式
单例模式
观察者模式
工厂模式
责任链模式

## 6.页面基类BaseActivity和BaseFragment

## 7.自定义view AngleCircleImageView

## 8.使用测试框架MockK编写单元测试，mockwebserver作为辅助工具用来模拟Web服务器