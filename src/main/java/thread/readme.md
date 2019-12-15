### 一个线程的生命周期

新建状态、就绪状态、运行状态、阻塞状态、死亡状态

### 线程的优先级

取值范围是 1 （Thread.MIN_PRIORITY ） - 10 （Thread.MAX_PRIORITY ）

### Java 提供了三种创建线程的方法：

通过实现 Runnable 接口；
通过继承 Thread 类本身；
通过 Callable 和 Future 创建线程。

线程如何调度和切换？

### 几个概念：

线程同步
线程间通信
线程死锁
线程控制：挂起、停止和恢复