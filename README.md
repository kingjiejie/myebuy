### myebuy
### 技术描述：
该项目基于javaEE，前端使用jQuery和bootstrap，后台sevlet，使用ajax向后台发送数据
### 初始化模块：
游客访问商城首页时，首页初始化显示动态的菜单栏和导航条。左侧菜单栏和顶部导航都是使用jsp静态包含。中间商品展示是动态包含，根据不同的需求展示不同的页面。页面的动态展示实现于在页面初始化时，后台开始查询数据库，获取到相应的数据，然后将这些数据存储到session中，在前台使用EL表达式将数据展示到页面
### 登录模块：
前台用js进行非空验证，后台UserController中RequestMapping（“login”）接收到用户名和密码，并将用户名和密码传出输到Service层中对应的方法进行逻辑判断。Service中对参数进行非空校验，若是有误返回提示信息，将接收到的参数传递给Dao层查询数据库验证用户存在性和密码的正确性。在Controller中根据service的返回信息判断用户是否登入成功，若是成功将用户信息存储到session中，便于前台获取。
### 个人中心：
用户登录后点击个人中心，首页动态包含该页面。展示个人信息，可以对个人信息进行修改。也可以查看订单信息，购物车信息
### 购物车模块：
用户浏览商品时可以将商品加入购物车，后台将该商品id加入到购物车表，该表是user和商品的中间表，将用户id和商品id关联实现购物车功能
### 后台管理模块：
管理员登录后导航栏出现后台管理按钮，后台有用户模块，对用户信息进行增删改差；商品模块，对商品进行增删该查；类型模块，对商品类型进行管理，同样是增删该查；报表模块，统计商品数据和销售数据，以图表的形式展现。
