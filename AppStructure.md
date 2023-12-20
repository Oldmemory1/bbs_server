# 项目架构

## 数据库模型

约定:

增: add

删: remove

改: update

查: query

### `User`用户实体

用户名(主键) str

密码 str

### `UserInfo`用户信息实体

用户名(主键) str

用户昵称 str

用户个人签名 str

头像 str

我的帖子 List<str>

帖子收藏 List<str>

### `Post`帖子实体

帖子id(主键) str

发帖日期 str

发帖用户名 str

帖子标题 str

帖子内容 str

帖子图片 List<str>

点赞数 int

评论列表 List<str>

### `Review`评论实体

评论id(主键) str

评论日期 str

评论用户名 str

评论内容 str

点赞数 int
