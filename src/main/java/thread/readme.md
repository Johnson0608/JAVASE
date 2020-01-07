##  线程基础

### 一个线程的生命周期

新建状态、就绪状态、运行状态、阻塞状态、死亡状态

### 线程的优先级

取值范围是 1 （Thread.MIN_PRIORITY ） - 10 （Thread.MAX_PRIORITY ）

### Java 提供了三种创建线程的方法：

（1）通过实现 Runnable 接口；

（2）通过继承 Thread 类本身；

（3）通过 Callable 和 Future 创建线程。

线程如何调度和切换？

### 几个概念：

线程同步
线程间通信
线程死锁
线程控制：挂起、停止和恢复

## 线程池

材料：https://www.jianshu.com/p/8d68c4b9d12e

### 为什么要使用线程池

缺点

a、每次new Thread新建对象，性能差。

b、缺乏统一管理，可能无限制的新建线程，过多占用系统资源导致死机或OOM

优点

a、重用存在的线程，减少对象创建，消亡的开销

**b、有效控制最大并发线程数，提高系统资源利用率**

### 线程池实现原理

当线程提交一个任务时候  _（线程和任务有什么关系）_

ThreadPoolExecutor执行execute()分4种情况

a、若当前运行的线程少于corePoolSize,则创建新线程来执行任务(执行这一步需要获取全局锁)

b、若运行的线程多于或等于corePoolSize,则将任务加入BlockingQueue

c、若无法将任务加入BlockingQueue,则创建新的线程来处理任务(执行这一步需要获取全局锁)

d、若创建新线程将使当前运行的线程超出maximumPoolSize,任务将被拒绝,并调用RejectedExecutionHandler.rejectedExecution()

采取上述思路,是为了在执行execute()时,尽可能避免获取全局锁
在ThreadPoolExecutor完成预热之后（当前运行的线程数大于等于corePoolSize),几乎所有的execute()方法调用都是执行步骤b,而步骤b不需要获取全局锁

### 线程池创建

我们可以通过ThreadPoolExecutor来创建一个线程池

创建一个线程池时需要的参数

（1）corePoolSize(核心线程数量)

线程池中应该保持的主要线程的数量。

即使线程处于空闲状态，除非设置了allowCoreThreadTimeOut这个参数,

当提交一个任务到线程池时，若线程数量<corePoolSize，线程池会创建一个新线程放入works(一个HashSet)中执行任务。
即使其他空闲的基本线程能够执行新任务也还是会创建新线程,等到需要执行的任务数大于线程池基本大小时就不再创建,会尝试放入等待队列workQueue(一个BlockingQueue),
如果调用了线程池的prestartAllCoreThreads(),线程池会提前创建并启动所有核心线程

（2）workQueue

存储待执行任务的阻塞队列，这些任务必须是Runnable的对象（如果是Callable对象，会在submit内部转换为Runnable对象）
-- （为什么？？）

runnableTaskQueue(任务队列):用于保存等待执行的任务的阻塞队列.可以选择以下几个阻塞队列.
LinkedBlockingQueue:一个基于链表结构的阻塞队列,此队列按FIFO排序元素,吞吐量通常要高于ArrayBlockingQueue.静态工厂方法Executors.newFixedThreadPool()使用了这个队列
SynchronousQueue:一个不存储元素的阻塞队列.每个插入操作必须等到另一个线程调用移除操作,否则插入操作一直处于阻塞状态,吞吐量通常要高于Linked-BlockingQueue,静态工厂方法Executors.newCachedThreadPool使用了这个队列

（3）maximumPoolSize（线程池最大线程数）

线程池允许创建的最大线程数
若队列满,并且已创建的线程数小于最大线程数,则线程池会再创建新的线程放入works中执行任务,CashedThreadPool的关键,固定线程数的线程池无效
若使用了无界任务队列,这个参数就没什么效果

（4）ThreadFactory

用于设置创建线程的工厂,可以通过线程工厂给每个创建出来的线程设置更有意义的名字.使用开源框架guava提供ThreadFactoryBuilder可以快速给线程池里的线程设置有意义的名字,代码如下
new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();

RejectedExecutionHandler（饱和策略）:当队列和线程池都满,说明线程池处于饱和,必须采取一种策略处理提交的新任务.策略默认AbortPolicy,表无法处理新任务时抛出异常.在JDK 1.5中Java线程池框架提供了以下4种策略

AbortPolicy：丢弃任务，抛出 RejectedExecutionException
CallerRunsPolicy:只用调用者所在线程来运行任务,有反馈机制，使任务提交的速度变慢）。
DiscardOldestPolicy:若没有发生shutdown,尝试丢弃队列里最近的一个任务,并执行当前任务, 丢弃任务缓存队列中最老的任务，并且尝试重新提交新的任务
DiscardPolicy:不处理,丢弃掉, 拒绝执行，不抛异常

当然,也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略.如记录日志或持久化存储不能处理的任务

（5）keepAliveTime（线程活动保持时间）

线程没有任务执行时最多保持多久时间终止
线程池的工作线程空闲后，保持存活的时间。
所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率

TimeUnit（线程活动保持时间的单位）:指示第三个参数的时间单位；可选的单位有天（DAYS）、小时（HOURS）、分钟（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）
可以使用Executors创建线程池

**创建线程池demo：ThreadPoolExample ThreadTaskId** 

