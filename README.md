Nacos：Spring Cloud Alibaba服务注册与配置中心   https://c.biancheng.net/springcloud/nacos.html

我们可以将 Nacos 理解成服务注册中心和配置中心的组合体，它可以替换 Eureka 作为服务注册中心，实现服务的注册与发现；还可以替换 Spring Cloud Config 作为配置中心，实现配置的动态刷新

Nacos 两大组件  与 Eureka 类似，Nacos 也采用 CS（Client/Server，客户端/服务器）架构

服务注册中心（Register Service）：它是一个 Nacos Server，可以为服务提供者和服务消费者提供服务注册和发现功能。
服务提供者（Provider Service）：它是一个 Nacos Client，用于对外服务。它将自己提供的服务注册到服务注册中心，以供服务消费者发现和调用。
服务消费者（Consumer Service）：它是一个 Nacos Client，用于消费服务。它可以从服务注册中心获取服务列表，调用所需的服务



注册中心：http://localhost:8848/nacos


搭建服务提供者
http://localhost:8001/dept/nacos/1

搭建服务消费者
http://localhost:8801/consumer/dept/nacos/1



Nacos 配置中心
${prefix}-${spring.profiles.active}.${file-extension}
dataId 格式中各参数说明如下：
${prefix}：默认取值为微服务的服务名，即配置文件中 spring.application.name 的值，我们可以在配置文件中通过配置 spring.cloud.nacos.config.prefix 来指定。
${spring.profiles.active}：表示当前环境对应的 Profile，例如 dev、test、prod 等。当没有指定环境的 Profile 时，其对应的连接符也将不存在， dataId 的格式变成 ${prefix}.${file-extension}。
${file-extension}：表示配置内容的数据格式，我们可以在配置文件中通过配置项 spring.cloud.nacos.config.file-extension 来配置，例如 properties 和 yaml。