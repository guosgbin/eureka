* 注册中心 **Eureka**
    * [《Eureka 源码解析 —— 项目结构简介》](http://www.iocoder.cn/Eureka/project-structure?github&1607)
    * [《Eureka 源码解析 —— 调试环境搭建》](http://www.iocoder.cn/Eureka/build-debugging-environment?github&1607)
    * [《Eureka 源码解析 —— Eureka-Client 初始化（一）之 EurekaInstanceConfig》](http://www.iocoder.cn/Eureka/eureka-client-init-first?github&1607)
    * [《Eureka 源码解析 —— Eureka-Client 初始化（二）之 EurekaClientConfig》](http://www.iocoder.cn/Eureka/eureka-client-init-second?github&1607)
    * [《Eureka 源码解析 —— Eureka-Client 初始化（三）之 EurekaClient》](http://www.iocoder.cn/Eureka/eureka-client-init-third?github&1607)
    * [《Eureka 源码解析 —— Eureka-Server 启动（一）之 ServerConfig》](http://www.iocoder.cn/Eureka/eureka-server-init-first?github&1607)
    * [《Eureka 源码解析 —— Eureka-Server 启动（二）之 EurekaBootStrap》](http://www.iocoder.cn/Eureka/eureka-server-init-second?github&1607)
    * [《Eureka 源码解析 —— 注册表 InstanceRegistry 类关系》](http://www.iocoder.cn/Eureka/instance-registry-class-diagram?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（一）之注册》](http://www.iocoder.cn/Eureka/instance-registry-register?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（二）之续租》](http://www.iocoder.cn/Eureka/instance-registry-renew?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（三）之下线》](http://www.iocoder.cn/Eureka/instance-registry-cancel?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（四）之自我保护机制》](http://www.iocoder.cn/Eureka/instance-registry-self-preservation?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（五）之过期》](http://www.iocoder.cn/Eureka/instance-registry-evict?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（六）之全量获取》](http://www.iocoder.cn/Eureka/instance-registry-fetch-all?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（七）之增量获取》](http://www.iocoder.cn/Eureka/instance-registry-fetch-delta?github&1607)
    * [《Eureka 源码解析 —— 应用实例注册发现（八）之覆盖状态》](http://www.iocoder.cn/Eureka/instance-registry-override-status?github&1607)
    * [《Eureka 源码解析 —— Eureka源码解析 —— 应用实例注册发现 （九）之岁月是把萌萌的读写锁》](http://www.iocoder.cn/Eureka/instance-registry-read-write-lock?github&1607)
    * [《Eureka 源码解析 —— 任务批处理》](http://www.iocoder.cn/Eureka/batch-tasks?github&1607)
    * [《Eureka 源码解析 —— EndPoint 与 解析器》](http://www.iocoder.cn/Eureka/end-point-and-resolver?github&1607)
    * [《Eureka 源码解析 —— 网络通信》](http://www.iocoder.cn/Eureka/transport?github&1607)
    * [《Eureka 源码解析 —— Eureka-Server 集群同步》](http://www.iocoder.cn/Eureka/server-cluster?github&1607)
    * [《Eureka 源码解析 —— 基于令牌桶算法的 RateLimiter》](http://www.iocoder.cn/Eureka/rate-limiter?github&1607)
    * [《Eureka 源码解析 —— StringCache》](http://www.iocoder.cn/Eureka/string-cache?github&1607)

[![Build Status](https://netflixoss.ci.cloudbees.com/job/NetflixOSS/job/eureka/job/eureka-snapshot/badge/icon)](https://netflixoss.ci.cloudbees.com/job/NetflixOSS/job/eureka/job/eureka-snapshot/)

Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers.

At Netflix, Eureka is used for the following purposes apart from playing a critical part in mid-tier load balancing.

* For aiding Netflix Asgard - an open source service which makes cloud deployments easier, in  
    + Fast rollback of versions in case of problems avoiding the re-launch of 100's of instances which 
      could take a long time.
    + In rolling pushes, for avoiding propagation of a new version to all instances in case of problems.

* For our cassandra deployments to take instances out of traffic for maintenance.

* For our memcached caching services to identify the list of nodes in the ring.

* For carrying other additional application specific metadata about services for various other reasons.


Building
----------
The build requires java8 because of some required libraries that are java8 (servo), but the source and target compatibility are still set to 1.7.


Support
----------
[Eureka Google Group](https://groups.google.com/forum/?fromgroups#!forum/eureka_netflix)


Documentation
--------------
Please see [wiki](https://github.com/Netflix/eureka/wiki) for detailed documentation.
