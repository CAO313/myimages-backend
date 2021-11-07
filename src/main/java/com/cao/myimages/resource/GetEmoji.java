package com.cao.myimages.resource;

import com.cao.myimages.entity.Emoji;
import com.cao.myimages.service.EmojiService;
import com.cao.myimages.utils.FTPUtils;
import com.cao.myimages.utils.FileUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/get")
public class GetEmoji {
    @Resource
    private EmojiService emojiService;
    @GetMapping("/emoji")
    public void getRsource()throws IOException {
        //https://image.dbbqb.com/202111071531/fd6a2fdc512ae3020f88830afa6e83e6/BL7n1
        for (int k = 20; k < 35; k++) {
            Map<String, String> header = new HashMap<String, String>();


            //           header.put("Host", "www.phizhub.com");
            header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");
//            header.put("Accept", "application/json, text/javascript, */*; q=0.01");
//            header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
//            header.put("Accept-Encoding", "gzip, deflate");
            header.put("Connection", "keep-alive");
            header.put("cookie", "PHPSESSID=9sc4nfthgn7nekeh7o6pdspd3q");
            //       header.put("Cookie", "gr_user_id=10bfcb70-257b-42d3-8f67-dfa9123a912e; gr_session_id_a103dce5677f1b74=0290cd41-d4aa-4eca-b82a-f2aad19acb89; gr_session_id_a103dce5677f1b74_0290cd41-d4aa-4eca-b82a-f2aad19acb89=false");
            //        long time = new Date().getTime();
            //       String md = "phizhub_abc_" + time;
            //      String md5Str = DigestUtils.md5DigestAsHex(md.getBytes());
            //       header.put("sss", md5Str);
            //         header.put("timestamp", String.valueOf(time));
            //         header.put("X-Requested-With", "XMLHttpRequest");
            Connection connection = Jsoup.connect("https://fabiaoqing.com/biaoqing/lists/page/" + k + ".html").ignoreContentType(true);
            connection.headers(header);
            Connection.Response response = connection.execute();
            String body = response.body();
            //       JSONObject object = JSONObject.fromObject(body);
            //     System.out.println(object);
            Document jsoup = Jsoup.parseBodyFragment(body);
            FTPUtils ftpUtils = new FTPUtils();
            Elements imgs = jsoup.select("img");
            for (Element img : imgs) {
                Emoji emoji = new Emoji();
                String url = img.attr("data-original");
                System.out.println("**************"+url);
                String name = img.attr("title");
                System.out.println(name);
                try {
                    File file = FileUtils.downloadFile(url);
                    String lastFix = url.substring(url.lastIndexOf('.'));
                    String filename = UUID.randomUUID() + lastFix;
                    FileInputStream inputStream = new FileInputStream(file);
                    ftpUtils.uploadFile("/home/www/pages", filename, inputStream);
                    url = "http://101.34.14.236:313/" + filename;
                    emoji.setUrl(url);
                    emoji.setTags("其他");
                    emoji.setName(name);
                    emoji.setReviewStatus(1);
                    emojiService.save(emoji);
                }catch (Exception e){
                    continue;
                }
            }

            //    JSONArray jsonArray = JSONArray.fromObject(object);


//            JSONArray data = object.getJSONArray("data");
//            FTPUtils ftpUtils = new FTPUtils();
//            for (int i = 0; i < data.size(); i++) {
//                Emoji emoji = new Emoji();
//                JSONObject dt = data.getJSONObject(i);
//                String url = dt.getJSONObject("image").getString("small");
//                System.out.println(url);
//                File file = FileUtils.downloadFile(url);
//                String lastFix = url.substring(url.lastIndexOf('.'));
//                String filename = UUID.randomUUID() + lastFix;
//                FileInputStream inputStream = new FileInputStream(file);
//                ftpUtils.uploadFile("/home/www/pages", filename, inputStream);
//                url = "http://101.34.14.236:313/" + filename;
////                emoji.setId(no);
//                emoji.setUrl(url);
//                emoji.setTags("热门");
//                emoji.setName("热门" + no);
//                emoji.setReviewStatus(1);
//                no++;
//                emojiService.save(emoji);
//            }
        }
    }
}