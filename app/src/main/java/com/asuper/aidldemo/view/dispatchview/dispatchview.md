```
    I/SuperActivity: dispatchTouchEvent
    I/SuperViewGroup: dispatchTouchEvent
    I/SuperViewGroup: onInterceptTouchEvent
    I/SuperView: dispatchTouchEvent
    I/SuperView: onTouchEvent
    I/SuperViewGroup: onTouchEvent
    I/SuperActivity: onTouchEvent
    I/SuperActivity: dispatchTouchEvent
    I/SuperActivity: onTouchEvent
    I/SuperActivity: dispatchTouchEvent
    I/SuperActivity: onTouchEvent
    I/SuperActivity: dispatchTouchEvent
    I/SuperActivity: onTouchEvent
    I/SuperActivity: dispatchTouchEvent
    I/SuperActivity: onTouchEvent
  ```
  
  事件分发是从activity -> viewgroup -> view顺序分发下去
  首先是action_down到达activity的dispatchTouchEvent方法，默认是调用super的dispatchTouchEvent方法
  viewgroup的dispatchTouchEvent方法内调用interceptTouchEvent,返回false不拦截该事件返回true则拦截该
  事件在viewgroup的onTouchEvent方法中处理该事件，返回false则继续向下传递给view的dispatchTouchEvent方法
  view中dispatchTouchEvent方法中调用onTouchEvent方法， 返回true则消费该事件，返回false则不消费该事件向上
  传递给viewgroup的onTouchEvent方法，该方法同理true则消费该事件，false则返回给activity的onTouchEvent方法
  如果传递到activity的onTouchEvent方法中都未消费该事件，则后续的action_move,action_down不会继续向下传递
  直接在activity中的onTouchEvent中消费，如上面日志所看到的。
  