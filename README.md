# 在线教育平台文档

## 一、功能设计

###### 1、首页

* 课程列表的搜索（课程名称和作者名字的模糊查询）
* 课程类型快速索引，显示类型名
* 热门推荐课程快速索引，显示课程名、作者名字、章节数、订阅数、点赞数

###### 2、登录

* 登录功能：输入用户名，密码，验证码进行登录

###### 3、注册

* 注册功能：输入用户名，密码，确认密码，邮箱，手机号码进行注册

###### 4、个人中心

* 课程列表的搜索（课程名称和作者名字的模糊查询）

* 个人信息：用户名、头像、真实姓名、邮箱、手机号码的显示和修改，以及密码的修改

* 我的订阅：

  * 用户所订阅课程的列表，显示课程名称、课程所属类型

  * 点击课程可以进入该课程的展示页面
  * 设置取消订阅按钮

* 我的收藏：

  * 用户所收藏视频的列表，显示视频名称，视频所在课程的所属类型
  * 点击视频进入该视频的播放页面
  * 设置取消收藏按钮

###### 5、创作中心

* 开设课程： 
  * 输入课程名称、选择课程类型新建课程
  * 用户已创建课程的列表，显示课程名、章节数、订阅数、点赞数
  * 点击课程名称进入该课程的展示页面
* 内容上传：
  
  * 选择或拖拽视频文件，选择已创建课程，选择已创建章节或新建章节，填写视频名称、视频简介进行内容上传
* 内容管理：
  
  * 课程列表
  
    * 用户已创建课程的列表，显示课程名称，课程所属分类，创建时间，订阅人数
    * 根据课程名称进行模糊查询
    * 设置修改和删除按钮，其中修改只能修改课程简介
    * 点击课程进入该课程下的章节列表
  * 章节列表
  
    * 用户所点击的课程下的章节列表，显示章节名称，创建时间，视频个数，点赞总数
    * 根据章节名称进行模糊查询
    
    * 设置修改和删除按钮，其中修改只能修改章节名称
    * 点击章节进入该章节下的视频列表
  * 视频列表
    * 用户所点击的章节下的视频列表，显示视频名称，上传时间，收藏数量，点赞数量
    * 根据视频名称进行模糊查询
    * 设置修改和删除按钮，其中修改包括视频名称，视频简介，以及移动到其他章节或新建章节
  

###### 6、直播中心

* 直播设置

  * 显示个人直播相关信息，包括昵称、头像、直播间地址、rtmp地址、推流码，其中点击直播间地址可以进入用户自己的直播间
  * 直播教程：点击如何直播，弹出显示直播教程的弹窗，其中包括OBS安装包的下载
  * 直播间的设置
    * 直播间标题的设置，默认为“XXX的直播间”，XXX为用户的昵称
    * 个人简介的设置
    * 开始直播和关闭直播的按钮，点击开始直播后直播间才能被搜索到，直播间才能呈现直播画面
 * 回放管理
    * 直播回放的列表，显示回放视频文件的名称，生成时间、视频时长
    * 设置删除按钮

###### 7、课程列表

* 所有课程的列表，显示课程名称、章节数、作者名字、订阅数、点赞数
* 课程列表的搜索（课程名称和作者名字的模糊查询）
* 根据课程所属类型进行分类显示，其中课程类型具有层级关系
* 根据创建时间、订阅数、点赞数进行排序

###### 8、直播间列表

* 所有直播间的列表，显示直播间名称、主播名字、是否正在直播
* 切换显示正在直播或所有直播间
* 直播间列表的搜索（直播间名称和主播名字的模糊查询）

###### 9、课程展示

* 显示课程名称、课程简介、作者头像、昵称
* 显示该课程的目录结构，可以播放所点击的视频
* 播放视频
  * 默认播放第一个章节的第一个视频，点击课程的目录结构，切换正在播放的视频
  * 播放视频可以暂停、播放、拖动进度条、调整播放速率、调整声音大小、全屏
  * 视频下方设置点赞按钮、收藏按钮、订阅按钮、显示点赞数、收藏数、订阅数
* 评论区：根据层级关系展示浏览学习者以及视频发布者的互动和讨论
* 课程列表的搜索（课程名称和作者名字的模糊查询）

###### 10、直播间

* 显示直播间名称、直播间简介，直播画面，主播名称，其中直播画面可以暂停、播放、调整声音大小、全屏
* 直播回放的视频列表
  * 显示录像名称、时长、生成时间，点击录像名称，播放回放视频
  * 回放视频可以暂停、播放、拖动进度条、调整播放速率、调整声音大小、全屏
* 直播间列表的搜索（直播间名称和主播名字的模糊查询）



  















