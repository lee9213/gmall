package com.lee9213.user;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 11:34
 */
public class DuridTest {


    @Test
    public void generatorPassword(){
        try {
            ConfigTools.main(new String[]{"123456"});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
