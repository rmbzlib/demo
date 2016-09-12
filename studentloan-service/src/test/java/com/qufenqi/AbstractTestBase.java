package com.qufenqi;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration("classpath:application-test.xml")
public abstract class AbstractTestBase {

    static String url = "http://123.56.104.144:10000/channel-server/daifu/payReal.do";
    static String param = "";
    static{
        param = "{\n" +
                "    \"text\" : \"5L8FKPy3b/cK4TDCmUmcaWpGrE6aywY30ZyscUtk7EFjF9WLGuY6j6d8JxdKG+XkwTWl96FuJr6DPzCIy/XyRSc6pxF2IcenbbVoww8CesKDLEMWq59tmLhLz8nNHAoz3LzNOta57CJMezrw/ETKQ02kpvjw3fs5QVLSa35wguAPb09p4ETFZExfUqM24rRCQb88PrsaPPphaD6EhXTgjBIuj7arYy/MXw46hDKs+nt1VqbDe8Gs4U0qpwpIOb3NnPoM5P7AqhTIKZQz9Ncm2z0+ZrVjmsjBhZgMJ3LKDE3VWyvawxSbTo+lSQT4oSXR0WVYp4Z51wiiLdZ6kxlUjgFtoXgrlMIe/0o6PHxT67zBa9bHDVYchSQQR1jFLQb8D+YCot6djHWTiJiOSRbWg9Xq5Rey1IISG459rFM0lTc=\n" +
                "\",\n" +
                "    \"bizCode\" : \"QUFENQI\",\n" +
                "    \"sign\" : \"9b1afae1b962b344d87a50f17cdca965\"\n" +
                "}";
    }
}

