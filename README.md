#### 介绍
基于spring boot + shiro 开发从前端到后台完整的权限管理平台模板

#### 软件架构
spring boot 2.0 + [layui](https://www.layui.com/doc/) + shiro(权限管理) + mysql + redis 代码开发工具idea(记得装lombok插件)

#### 项目架构

+ ciifm-personal-platform
    + ciifm-personal-admin
		+ ciifm-personal-admin-app
		+ ciifm-personal-admin-dao
		+ ciifm-personal-admin-component
		+ ciifm-personal-admin-provider
		+ ciifm-personal-admin-share


项目架构说明

1. app
	项目启动、静态文件、配置文件存放
2. dao
	负责与数据库交互、实体类、DAO接口以及mapper配置文件存放
3. component
	负责与dao层交互,以及自定义接口定义实现
4. provider
	整个项目业务逻辑处理集中在这层
5. share
	对外接口定义

### 项目maven依赖

```seq
app ->provider: 依赖
provider->component: 依赖
component->dao: 依赖
provider-->share: 依赖
Note right of share: 对外接口
```
### End

#### 使用说明

1. 文件依赖    [ciifm-file](https://www.layui.com/doc/)
```xml
<dependency>
       <groupId>com.github.ciifm</groupId>
       <artifactId>ciifm-file</artifactId>
       <version>1.0.0</version>
</dependency>
```

2. 框架依赖   [handy-framework](https://www.layui.com/doc/)
```xml
<dependency>
      <groupId>com.github.ciifm</groupId>
      <artifactId>handy-framework</artifactId>
      <version>1.0.0</version>
</dependency>
```

3. 添加github仓库（上面2个依赖需要添加该仓库才能使用）
```xml
<repositories>
    <repository>
      <id>ciifm-repo</id>
      <url>https://raw.githubusercontent.com/ciifm/maven-repo-public/master</url>
    </repository>
</repositories>
```

4. 基础数据
> 项目sql文件夹下表结构以及基础数据sql脚本

4. 特别说明
> 项目下载之后先编译,如有发现编辑报错类型为依赖下游报错的,解决方法为从依赖下层开始往上面install（dao=share -> component -> provider -> app -> admin -> platform）,逐层解决问题。具体就不多说,不清楚可以自行百度maven多模块项目编译构建相关问题
