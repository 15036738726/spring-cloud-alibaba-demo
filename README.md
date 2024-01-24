Seata：Spring Cloud Alibaba分布式事务组件   https://c.biancheng.net/springcloud/seata.html

分支：Branche_Seata

在分布式微服务架构中，几乎所有业务操作都需要多个服务协作才能完成。对于其中的某个服务而言，它的数据一致性可以交由其自身数据库事务来保证，
但从整个分布式微服务架构来看，其全局数据的一致性却是无法保证的

例如，用户在某电商系统下单购买了一件商品后，电商系统会执行下 4 步：

调用订单服务创建订单数据
调用库存服务扣减库存
调用账户服务扣减账户金额
最后调用订单服务修改订单状态

为了保证数据的正确性和一致性，我们必须保证所有这些操作要么全部成功，要么全部失败

Seata 就是这样一个分布式事务处理框架，它是由阿里巴巴和蚂蚁金服共同开源的分布式事务解决方案，能够在微服务架构下提供高性能且简单易用的分布式事务服务

分布式事务主要涉及以下概念：
事务：由一组操作构成的可靠、独立的工作单元，事务具备 ACID 的特性，即原子性、一致性、隔离性和持久性。
本地事务：本地事务由本地资源管理器（通常指数据库管理系统 DBMS，例如 MySQL、Oracle 等）管理，严格地支持 ACID 特性，高效可靠。本地事务不具备分布式事务的处理能力，隔离的最小单位受限于资源管理器，即本地事务只能对自己数据库的操作进行控制，对于其他数据库的操作则无能为力。
全局事务：全局事务指的是一次性操作多个资源管理器完成的事务，由一组分支事务组成。
分支事务：在分布式事务中，就是一个个受全局事务管辖和协调的本地事务


我们可以将分布式事务理解成一个包含了若干个分支事务的全局事务。全局事务的职责是协调其管辖的各个分支事务达成一致，要么一起成功提交，要么一起失败回滚。
此外，通常分支事务本身就是一个满足 ACID 特性的本地事务。


Seata 对分布式事务的协调和控制，主要是通过 XID 和 3 个核心组件实现的。
XID
XID 是全局事务的唯一标识，它可以在服务的调用链路中传递，绑定到服务的事务上下文中

核心组件
Seata 定义了 3 个核心组件：
TC（Transaction Coordinator）：事务协调器，它是事务的协调者（这里指的是 Seata 服务器），主要负责维护全局事务和分支事务的状态，驱动全局事务提交或回滚。
TM（Transaction Manager）：事务管理器，它是事务的发起者，负责定义全局事务的范围，并根据 TC 维护的全局事务和分支事务状态，做出开始事务、提交事务、回滚事务的决议。
RM（Resource Manager）：资源管理器，它是资源的管理者（这里可以将其理解为各服务使用的数据库）。它负责管理分支事务上的资源，向 TC 注册分支事务，汇报分支事务状态，驱动分支事务的提交或回滚。

以上三个组件相互协作，TC 以 Seata 服务器（Server）形式独立部署，TM 和 RM 则是以 Seata Client 的形式集成在微服务中运行

Seata 的整体工作流程如下：
TM 向 TC 申请开启一个全局事务，全局事务创建成功后，TC 会针对这个全局事务生成一个全局唯一的 XID；
XID 通过服务的调用链传递到其他服务;
RM 向 TC 注册一个分支事务，并将其纳入 XID 对应全局事务的管辖；
TM 根据 TC 收集的各个分支事务的执行结果，向 TC 发起全局事务提交或回滚决议；
TC 调度 XID 下管辖的所有分支事务完成提交或回滚操作。



上传配置到 Nacos 配置中心
我们可以通过 Seata Server 源码/script/config-center 目录中获取 config.txt，然后根据自己需要修改其中的配置，如下图
https://github.com/seata/seata/archive/refs/tags/v1.4.2.zip
中获取

在 seata-1.4.2\script\config-center\nacos 目录下，右键鼠标选择 Git Bush Here，并在弹出的 Git 命令窗口中执行以下命令，
将 config.txt 中的配置上传到 Nacos 配置中心
sh nacos-config.sh -h 127.0.0.1 -p 8848  -g SEATA_GROUP -u nacos -w nacos

-h：Nacos 的 host，默认取值为 localhost
-p：端口号，默认取值为 8848
-g：Nacos 配置的分组，默认取值为 SEATA_GROUP
-u：Nacos 用户名
-w：Nacos 密码



Seata Server 配置
下载zip,修改配置文件 config/registry.conf 
registry.nacos(Seata Server 配置注册中心) config.nacos(Seata Server 配置)都修改为nacos对应的配置,然后启动  


启动nacos 
打开命令行窗口，跳转到 Nacos Server 安装目录的 bin 下，执行以下命令，以单机模式启动 Nacos Server 
startup.cmd -m standalone
启动sentinel
java -jar sentinel-dashboard-1.8.4.jar
启动seata
启动脚本 seata-server.bat


正常创建订单,扣减库存,扣减账余额
http://localhost:8005/order/create/1/2/20

余额不足,但未完成数据回滚
http://localhost:8005/order/create/1/10/1000


@GlobalTransactional 注解

@GlobalTransactional 注解
在分布式微服务架构中，我们可以使用 Seata 提供的 @GlobalTransactional 注解实现分布式事务的开启、管理和控制。

当调用 @GlobalTransaction 注解的方法时，TM 会先向 TC 注册全局事务，TC 生成一个全局唯一的 XID，返回给 TM。

@GlobalTransactional 注解既可以在类上使用，也可以在类方法上使用，该注解的使用位置决定了全局事务的范围，具体关系如下：
在类中某个方法使用时，全局事务的范围就是该方法以及它所涉及的所有服务。
在类上使用时，全局事务的范围就是这个类中的所有方法以及这些方法涉及的服务。

余额不足,完成数据回滚
http://localhost:8005/order/createByAnnotation/1/10/1000



