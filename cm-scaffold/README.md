- 过滤器
- 拦截器
- 跨域配置
- KnifeSwagger后端文档接口
- SpringSecurity框架
- JWT（待做）
- 代码生成（待做，自定义模板方式）
- 统一拦截配置
- Log4j2配置
- BaseController、Utils等基类工具类封装
- 考虑MapStruct是否有必要接入（待做）
- JUnit测试
- Vue简单的搭建融合（待做）

参考ruoyi-vue项目，多模块组织结构，代码生成器可以单独抽取出来（若依是整合在具体项目中） 
可以精简一下，不必要全部拷贝，功能还是要全，好扩展，后续考虑spring cloud。

1. 异常处理参考若依，全局异常处理统一挪动到通用common下，可自定义设置异常类 
https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html
https://www.cnblogs.com/UncleWang001/p/10949318.html 
2. 阿里巴巴--可乐脚手架
https://www.cnblogs.com/alisystemsoftware/p/13273407.html
