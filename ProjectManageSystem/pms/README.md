

## 技术栈

需要`Java 8`环境，推荐使用`IDEA`作为开发工具，所用到的技术：

1. Spring Boot v2.0.5
2. MySQL
3. Freemark
4. SiteMesh
5. Shiro
6. Bootstrap
7. mybatis、mybatisPlus
8. redis
9. Activiti v5.22

**需要以前版本可以切换至master分支**

## 部署

pms是使用`Maven`构建的多模块项目，分模块开发，各模块可插拔。`pms-web`项目是pms的主入口，在`pms-web`的`pom`文件中引入需要的模块之后，通过以下步骤来启动项目：

- 导入数据库

  在项目的`db`文件夹下有数据库脚本，首先导入数据。

- 启动`redis`服务

  需本地安装`redis`或者其他远程`redis`。

- 修改相关配置

  修改`web`下`application-dev.yml`中的配置：

  1. 数据库相关配置
  2. `redis`相关配置

- 启动项目

  启动`web`工程下的`WebApplication`。

## 开发

开发上pms做了一些限制，或者叫约定：

1. 编码约定

系统分为`controller`、`service`、`dao`层。
`controller`主要负责转发、`service`主要负责业务逻辑、`dao`主要是数据库的操作。

2. 文件名称约定

   在页面文件夹中，按照功能模块分别建立不同的文件夹存放页面，如用户的页面就放在`user`文件夹中，而角色的就放在`role`文件夹中。

   - 页面如果是列表类型的。页面的文件名用`list.ftl`命名。

   - 页面如果是详情类型的。页面的文件名用`detail.ftl`命名。

3. `controller`、`service`、`dao`方法名称约定

   - 如果是增加数据操作用`insert`做前缀。

   - 如果是删除操作用`delete`做前缀

   - 如果是修改操作用`update`做前缀

   - 如果是查询操作用`select`做前缀

若是要新建模块开发，可以按照以下步骤进行：

1. new Module 

2. GroupId --->com.pms 

3. ArtifactId---> pms-模块名称   如  pms-activiti

4. Version --> 版本号   如 1.0SNAPSHOT 

5. Module-Name--> pms-模块名称   如  pms-activiti 

6. 提交新建模块 

7. pom 文件引入

   ```xml
   <name>pms-模块名称</name>
   
       <dependencies>
           <dependency>
               <groupId>com.pms</groupId>
               <artifactId>pms-common</artifactId>
           </dependency>
   
           .
           .
           .其他的依赖
           .
       </dependencies>
       
       <build>
           <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <version>3.6.1</version>
                   <configuration>
                       <source>1.8</source>
                       <target>1.8</target>
                       <encoding>UTF-8</encoding>
                       <compilerArgs>
                           <arg>-parameters</arg>
                       </compilerArgs>
                       <useIncrementalCompilation>false</useIncrementalCompilation>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   ```


新建完成模块之后需要继续功能性开发，可按照以下步骤：

1. 创建数据库

2. 创建`entity`类

3. 创建`service`类

4. 创建`controller`类

5. 创建列表界面

   - 将其他功能的`list.ftl`文件复制一份过来。

   - 修改`var url = "${base}/sys/user/"`为刚刚创建的`Controller`所以对应的`@RequestMapping`的值。

   - 修改搜索条件。

   - ```java
     /**
      * 等于
      */
     public static final String SEARCH_EQ="search_eq_";
     
     /**
      * 左模糊
      */
     public static final String SEARCH_LLIKE="search_llike_";
     
     /**
      * 右模糊
      */
     public static final String SEARCH_RLIKE="search_rlike_";
      
     /**
      * 全模糊
      */
     public static final String SEARCH_LIKE="search_like_";
     
     
     ```

     在模版文件中

     > <input type="text" class="form-filter input-sm _search" name="search_eq_login_name"> 

     只要在`input`中的`name`加入`search_eq_` 前缀 再加数据库中的字段名称即可。

   - 修改对应字段名称。

emmm，其实我觉得这部分好像没必要写的。




