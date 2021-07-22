package com.hbsoo.starter

import com.hbsoo.starter.model.User

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by zun.wei on 2021/7/21.
 *
 */
class StarterImpl implements IStarter {


    @Override
    int add(int a, int b) {
        a + b
    }

    @Override
    String getUserName(Long id) {
        "李四$id"
    }

    @Override
    long testClosure(Closure closure) {
        def numbers = [1, 2, 3]
        String[] arrStr = ['Ananas', 'Banana', 'Kiwi'] //直接声明类型为数组类型  String[]
        // key虽然没有加引号，不过Groovy会默认将其转换为字符串
        def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']
        def range = 0..5

        for (String str : arrStr) {
            println str
        }

        for (x in range) {
            println x
        }

        for (c in colors) {
            println c.key + "---" +c.value
        }

        println "--------------"
        for (n in numbers) {
            //closure("class is $n")
            closure(n)
        }
         0
    }

    @Override
    void execute(Long times) {
        def c = new CountDownLatch((int) times)

        def var = new AtomicInteger()
        new Thread(() -> {
            for (x in 0..times) {
                def i = var.incrementAndGet()
                println "count $i"
                c.countDown()
                sleep(1000)
            }
        }).start();

        c.await()

        println "execute finish"
    }

    @Override
    User getUserById(Long id) {
        def user = new User()
        user.id =111L
        user.username = "zhans"
        user.password = "123"
        user
    }
}
