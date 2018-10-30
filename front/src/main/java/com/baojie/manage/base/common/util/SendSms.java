package com.baojie.manage.base.common.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class SendSms {
    protected final static Logger logger = Logger.getLogger(SendSms.class);
    static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
    
    private static String ENCODING = "UTF-8";
   /**
    * 发送短信
    * @param phoneNum
    * @param msgText
    * @return
    */
    public static boolean sendMsg(String phoneNum, String msgText) {
        
        return sendSms(phoneNum, msgText);
    }
    private static boolean sendSms(String phoneNum, String msgText)
    {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        String tpl_value = null;
        try
        {
             tpl_value = URLEncoder.encode("#code#", ENCODING) + "=" + URLEncoder.encode(msgText, ENCODING);
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

//        log.info(String.format("sendSms phone = %s , msgTest = %s ", phoneNum, tpl_value));

        try
        {
            //String apikey = PropertiesHelper.getString("yunpian-key");
           
            String apikey = propertiesLoader.getProperty("yunpian-key");
            // log.info(String.format("sendSms apikey = %s", apikey));

            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("apikey", apikey);
            paramsMap.put("tpl_value", tpl_value);
            paramsMap.put("tpl_id", propertiesLoader.getProperty("yunpian-templateId"));// 模板iD
            paramsMap.put("mobile", phoneNum);
            HttpPost method = new HttpPost(propertiesLoader.getProperty("yunpian-url"));
            if (paramsMap != null)
            {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet())
                {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                responseText = EntityUtils.toString(entity);
                logger.info("responseText="+responseText);  
                if (responseText.indexOf("发送成功") >= 0)
                {
                    return true;
                }
            }
//            log.info(String.format("sendSms return responseText = %s", responseText));
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                response.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

}
