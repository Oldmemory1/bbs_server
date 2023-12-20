# Assembled Sever接口文档

接口地址: `http://8.130.13.195:12345`

## /user [json]

### /add

添加一个用户

`post`:

```json
{
  "username": "username",
  "password": "password"
}
```

`response`:

```json
{
  "success": true//失败时返回false
  "userResponseFailedReason": null//失败时返回具体失败的原因
  "user": {
    "username": "username",
    "password": "password"
  }
}
```

```java
//枚举类型以枚举名字的字符串返回
//例如如果用户名不存在,则userResponseFailedReason字段的值是"USERNAME_DOES_NOT_EXIST"
public enum UserResponseFailedReason {
    USERNAME_ALREADY_EXIST,
    USERNAME_DOES_NOT_EXIST,
    WRONG_PASSWORD,
}
```

### /query

查询一个`user`

`post`:

与`/add`相同

`response`:

与`/add`相同

**注意, 除了user控制器, 其他的控制器请提交表单信息, 而不是json!!!**

## /userInfo [form-data]

### /query

查询用户信息

`post`:

```json
//查询用户信息只要求username字段有效,别的字段必须有,但是可以是任意值
{
  "username": "username",
  "nickname": "随便一个值, 也可以是null",
  "personalSign": "随便一个值, 也可以是null",
  "avatar":
  //头像文件, 可以视空
}
```

`get`:

发送请求到`/userInfo/query/{username}`

`axios`样例:

```js
var username = "mxy2233"
axios.get(`/userInfo/query/${username}`).then((response) => {
    const userInfo = response.data.userInfo;
    console.log(userInfo);
}).catch((error) => {
    console.log(error);
})
```

`response`:

```json
{
  "success": true,
  "userInfoResponseFailedReason": null,
  //失败时userInfo为null
  "userInfo": {
    "username": "username",
    "nickname": "nickname",
    "personalSign": "personal sign",
    //用户头像图片的地址
    "avatarUrl": "http://8.130.13.195:8086/home/nginx_root/assembled_server/server/avatars/default.png"
    "myPosts": [
      "post1",
      "post2"
    ],
    "myCollections": [
      "post1",
      "post1"
    ]
  }
}
```

```java
public enum UserInfoResponseFailedReason {
    USERNAME_DOES_NOT_EXIST
}
```

### /update

更新用户信息

`post`:

与`query`相同, 但是要求`avatar`字段不为空, 因为用户必须有一个头像.

`response`:

与`query`相同

## /post [form-data]

### /add

发一个帖子.

表单字段:

```json
{
  "id": "随机一个字符串作为帖子在数据库的主键",
  "owner": "发帖人的username",
  "titile": "猪肉涨价啦",
  "content": "快点吃"
  "images":
  //帖子图片, 让用户从本地选择
}
```

`reponse`:

```json
{
  "success": true,
  "postResponseFailedReason": null
  "post": {
    "id": "dsahuidhsadhsak",
    "date": "2023年1月8日",
    "owner": "mxy2233",
    "title": "猪肉涨价啦",
    "content": "快点吃",
    "images": [
      //帖子图片的url
      "http://8.130.13.195:8086/home/nginx_root/assembled_server/server/avatars/default.png"
    ]
    "likeNum": 12,
    //回复的id
    "reviews": [
      "dhsajkdshakdh",
      "hudhiiurye41521"
    ]
  }
}
```

```java
public enum PostResponseFailedReason {
    //当查询帖子id会失败会返回此值
    POST_DOES_NOT_EXIST,
    //postid与数据库中的某个postid重复返回此值(基本不会出现这种错误)
    POST_ALREADY_EXISTS,
}
```

### /query

表单字段与`/add`一致, 但只要求`id`有效.

`get`:

发送请求到`/post/query/{postId}`

`reponse`:

与`/add`一致