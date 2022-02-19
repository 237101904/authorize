package com.authorize.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("/demo")
public class Demo {

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg","Hello World!");
        mav.setViewName("/hello");
        return mav;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg","Hello World!");
        mav.setViewName("/index");
        return mav;
    }

    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }

    @RequestMapping("/helloString")
    public String helloString(){
        return "hello";
    }

    @RequestMapping("/indexString")
    public String indexString(){
        return "index";
    }

    @RequestMapping("/path1")
    public String indexPath1() {
        return "ok";
    }

    @RequestMapping("/path2")
    public String indexPath2(Model model) {
        return "path2";
    }

    @RequestMapping("/{variable}")
    @ResponseBody
    public String indexVarialble(@PathVariable("variable")String variable) {
        return variable;
    }

    @RequestMapping("/path3")
    @ResponseBody
    public String indexPath3(@RequestBody String body) {
        //对body进行解析即可
        return "success";
    }

    @RequestMapping("/weather")
    @ResponseBody
    public String weather(String urlString) throws Exception{
        if("".equals(urlString)||urlString == null)
            urlString = "http://api.k780.com/?app=weather.today&weaId=1&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        URL url = new URL(urlString);
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        //请求方式
        httpConn.setRequestMethod("POST");
        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
        httpConn.setDoOutput(true);
        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
        httpConn.setAllowUserInteraction(false);

        //处理返回结果
        BufferedReader bReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String line,resultStr="";
        while(null != (line=bReader.readLine()))
        {
            resultStr +=line;
        }
        System.out.println(resultStr);
        bReader.close();

        return resultStr;
    }

    @RequestMapping("/cmaData")
    @ResponseBody
    public String cmaData(String urlString) throws Exception {
        URL url = new URL("http://api.k780.com/?app=weather.today&weaId=1&appkey=APPKEY&sign=SIGN&format=json");
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        System.out.println(new String(b, "utf-8"));
        return "Ok";
    }
}
