服务不被杀死的方法：
1.提升service优先级
2.提升service进程优先级
3.onDestroy方法里重启service
启动方式：
1.startservice
    oncreate() -> onstart() -> ondestroy()
    只会调用一次 onCreate() 调用多次onstart() 一直驻在后台直到调用stopservice()
2.bindService
    oncreate() -> onBind() -> onUnBind() -> onDestroy()
    只会调用一次 onBind 生命周期和Activity保持一致

跨进程通讯
1.client、proxy、serviceManager、BinderDriver、impl、service
2.client发起一个请求service信息的Binder请求到BinderDriver中，serviceManager发现BinderDiriver中有自己的请求
 然后将clinet请求的service的数据返回给client这样完成了一次Binder通讯
3.clinet获取的service信息就是该service的proxy，此时调用proxy的方法，proxy将请求发送到BinderDriver中，
此时service的 Binder线程池循环发现有自己的请求，然后用impl就处理这个请求最后返回，这样完成了第二次Binder通讯
4.中间client可挂起，也可以不挂起，有一个关键字oneway可以解决这个

mvc、mvp、mvvm:
1.mvc:数据、View、Activity，View将操作反馈给Activity，Activitiy去获取数据，数据通过观察者模式刷新给View。循环依赖

1.Activity重，很难单元测试

2.View和Model耦合严重
2.mvp:数据、View、Presenter，View将操作给Presenter，Presenter去获取数据，数据获取好了返回给Presenter，Presenter去刷新View。PV，PM双向依赖

1.接口爆炸

2.Presenter很重
3.mvvm:数据、View、ViewModel，View将操作给ViewModel，ViewModel去获取数据，数据和界面绑定了，数据更新界面更新。

1.viewModel的业务逻辑可以单独拿来测试

2.一个view 对应一个 viewModel 业务逻辑可以分离，不会出现全能类

3.数据和界面绑定了，不用写垃圾代码，但是复用起来不舒服

