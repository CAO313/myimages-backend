package com.cao.myimages;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class JsoupTest {

    @Test
    public void jsoupTest() throws IOException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "www.phizhub.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Connection", "keep-alive");
        header.put("Referer", "http://www.phizhub.com/");
        header.put("Cookie", "gr_user_id=10bfcb70-257b-42d3-8f67-dfa9123a912e; gr_session_id_a103dce5677f1b74=d88a9a14-af00-4de0-a50b-29c8f4a3a195; gr_session_id_a103dce5677f1b74_d88a9a14-af00-4de0-a50b-29c8f4a3a195=false");
        long time = new Date().getTime();
        String md = "phizhub_abc_" + time;
        String md5Str = DigestUtils.md5DigestAsHex(md.getBytes());
        header.put("sss", md5Str);
        header.put("timestamp", String.valueOf(time));
        header.put("X-Requested-With", "XMLHttpRequest");
        Connection connection = Jsoup.connect("http://www.phizhub.com/phiz/get_phiz_list/?category=5&page=1&last_time=0&page_size=10").ignoreContentType(true);
        connection.headers(header);
        Connection.Response response = connection.execute();
        String body = response.body();
        JSONObject object = JSONObject.fromObject(body);
        JSONArray data = object.getJSONArray("data");
        for(int i=0;i<data.size();i++){
            JSONObject dt = data.getJSONObject(i);
            String url = dt.getJSONObject("image").getString("small");
            System.out.println(url);
        }
    }
}
