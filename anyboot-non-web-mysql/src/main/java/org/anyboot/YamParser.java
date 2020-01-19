package org.anyboot;

import org.anyline.util.BasicUtil;
import org.anyline.util.BeanUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamParser {
    public static void loadConfig() throws FileNotFoundException {
        //配置文件路径

        InputStream reader = YamParser.class.getResourceAsStream("/application.yml");

        //yml读取配置文件,指定返回类型为Map,Map中value类型为LinkedHashMap
        map = new Yaml().loadAs(reader, LinkedHashMap.class);


    }

    //yml读取配置文件,指定返回类型为Map,Map中value类型为LinkedHashMap
    static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> map;
    static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> mapOthers;


    public static void main(String[] args) throws Exception{
        //加载运行配置文件
        loadConfig();

        System.out.println(BeanUtil.value(map,"spring.s"));
    }
}
