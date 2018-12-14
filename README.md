# sso
实现简单的单点登录
simple-sso-a 登录后将
用户信息中userNo值作为 请求 simple-sso-b 请求头中 UserNoA 的值
simple-sso-b 过滤器中获取请求头中值 ,如果不为空 并且查询,用户关系表时查询查询到有对应关系就不用提示用户登录了

