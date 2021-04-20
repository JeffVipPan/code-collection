# code-collection
收集一些自用的代码，便于工作中copy。。。


## java-utils-collection  工具类

### PasswordHashHelper

用户密码加盐处理


### DateFormat

多时区下的日期处理工具类

### RetryUtils

某一段代码重试的工具类

### TraceIdGenerator

traceId生成,链路跟踪时使用


## spring-collection Spring一些常用做法

### EnvironmentContext

代码中经常存在一个静态方法想要获取Spring上下文中的参数信息,那么一共一个这样的类,来实现该功能.使用该类,要确保该类会很优先被Spring实例化出来.

### ConfigCacheManager

应用内注册中心实现, 配置数据只需要实现ConfigRegisterFactory接口即可接入注册中心


