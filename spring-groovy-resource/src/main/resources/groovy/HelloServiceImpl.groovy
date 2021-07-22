package groovy

import com.hbsoo.resouce.HelloService

/**
 * Created by zun.wei on 2021/7/22.
 *
 */
class HelloServiceImpl implements HelloService{

    String name;

    @Override
    String sayHello() {
        "Hello $name -- ${name}. Welcome to resource in Groovy."
    }
}
