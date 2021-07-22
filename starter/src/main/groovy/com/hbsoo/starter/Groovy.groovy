package com.hbsoo.starter

import com.hbsoo.starter.model.User

/**
 * Created by zun.wei on 2021/7/21.
 *
 */
class Groovy {

    static void main(String[] args) {
        IStarter starter = new StarterImpl()
        def var = starter.add(1, 1)
        println var

        def var1 = starter.getUserName(110)
        println var1


        //starter.testClosure {String x -> println x}
        //starter.testClosure {x -> println x}
        starter.testClosure {println it}

        starter.execute(3)

        def id = starter.getUserById(110L)
        println id

    }



}