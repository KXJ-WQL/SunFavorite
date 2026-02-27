# SunFavorite🌟空想家网页收藏夹
## 1. 主要功能是🌍
- 微信公众号验证码登录
- 个人网页收藏(公开收藏/私有收藏)
- 收藏广场，显示其他用户公开收藏
- 网页收藏搜索
- 个人中心(个人名称+头像)
## 2. 技术的选型和基础环境搭建配置是🪐
**技术的选型🛩：**
1. 数据库使用MySQL
2. 缓存使用Redis
3. 使用WXJava对接微信公众号
4. 搜索功能使用ElasticSearch，分词使用IK分词器
5. MySQL和ElasticSearch的数据同步使用Canal，这个Canal包含Canal-Server和Canal-Adapter
6. 持久层框架使用SpringData JPA(如果有复杂的查询不推荐)
7. 基础框架使用SpringBoot
8. 模板引擎使用FreeMarker
9. 前端使用BootStrap做布局，也使用到了Layui部分组件

**Docker基础环境的搭建博客🚀：**[点击进入](https://wql.luoqin.ltd/2022/10/29/sunfavorite%e9%a1%b9%e7%9b%ae%e5%9f%ba%e7%a1%80%e7%8e%af%e5%a2%83%e6%90%ad%e5%bb%ba/)

## 3. 拉下项目如何运行是🛰
**步骤🧭：**
1. 注册个人公众号
2. 修改application.yml文件中的配置
3. 使用Natapp做内网穿透进行测试
4. 修改前端的二维码

**Natapp内网穿透和公众号设置博客🚕：**[点击进入](https://wql.luoqin.ltd/2022/10/29/sunfavorite%e9%a1%b9%e7%9b%ae%e8%bf%90%e8%a1%8c/)

**修改application.yml：只列非常规设置，连接信息省略**
```yml
server:
  #设置为服务的IP地址或者内网穿透域名
  domain: http://u58e5j.natappfree.cc
wx:
  mp:
    #设置微信公众开发者ID和Token信息
    app-id: wxxx
    secret: wxxx
    token: wxxx
file:
  #文件上传映射地址
  staticAccessPath: /api/file/**
  #在指定地址创建目录
  uploadFolder: F:\KXJPageCollect\static\teamImages\
#其他就是redis,mysql,ES地址，这里省略
``` 
## 4. 项目演示图🌌
1, 登录页

![登录页](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/%E7%99%BB%E5%BD%95.png#pic_center "登录页")

2, 公众号获取验证码

![公众号获取验证码](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite6.png#pic_center "公众号获取验证码")

3, 个人收藏

![个人收藏](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite3.png#pic_center "个人收藏")

4, 公共收藏(点击他人名称可以进入他的收藏空间，但只显示公开的收藏目录)

![公共收藏](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite5.png#pic_center "公共收藏")

5, 新建收藏

![新建收藏](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite4.png#pic_center "新建收藏")

6, 收藏搜索(收藏名称和收藏备注都可以)

![收藏搜索](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite8.png#pic_center "收藏搜索")

7, 个人中心

![个人中心](https://gitee.com/WQL-KXJ/SunFavorite/raw/master/ProjectCaseImages/SunFavorite7.png#pic_center "个人中心")
