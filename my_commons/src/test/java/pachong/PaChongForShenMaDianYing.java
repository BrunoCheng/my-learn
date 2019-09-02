package pachong;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 爬取神马电影(多线程)
 * @Date 2019/8/30
 * @Created by acheng
 */
public class PaChongForShenMaDianYing {
    public static void main(String[] args) throws Exception {
        //1.必要的路径和存储位置
        //获取视频播放路径
        //以http://www.1688aiwan.com/show/25---VIP%E8%A7%86%E9%A2%91--------.html为例子,路径为
        String source = "http://www.1688aiwan.com/play/18hanguoxiaojiejieVIPshipin";
        //资源存储路径
        String rootPath = "D:\\pc\\";

        //获取视频的URL地址和视频名称存入hashMap
        Map<String, String> urlMap = getUrlInSource(source);

        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            String title = entry.getKey();// 视频名称
            System.out.println(title);
            String indexPath = entry.getValue();// 视频url
            //注意:播放界面为indexPath = "http://jx.577kp.com/dplayer/?url=https://135zyv6.xw0371.com/" + indexPath + ".m3u8";
            //单实际上只需要后面的"https://135zyv6.xw0371.com/" + indexPath + ".m3u8";部分,获取到索引即可
            indexPath = "https://135zyv6.xw0371.com/" + indexPath + ".m3u8";


            //这里只演示多线程

            String prePath = indexPath.substring(0, indexPath.lastIndexOf("/") + 1);
            //下载索引文件
            String indexStr = getIndexFile(indexPath);
            //解析索引文件
            List videoUrlList = analysisIndex(indexStr);
            //生成文件下载目录
            String uuid = title;

            String fileRootPath = rootPath + uuid;
            File fileDir = new File(fileRootPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //下载视频片段，分成50个线程切片下载
            HashMap keyFileMap = new HashMap();
            int downForThreadCount = 2;
            for (int i = 0; i < videoUrlList.size(); i += downForThreadCount) {
                int end = i + downForThreadCount - 1;
                if (end > videoUrlList.size()) {
                    end = videoUrlList.size() - 1;
                }
                new MutilPaChong().new downLoadNode(videoUrlList, i, end, keyFileMap, prePath, fileRootPath).start();
            }
            //等待下载
            while (keyFileMap.size() < videoUrlList.size()) {
                System.out.println("当前下载数量" + keyFileMap.size());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //合成视频片段
            composeFile(fileRootPath + uuid + ".mp4", keyFileMap);
        }

    }
    /**
     * 获取视频的URL地址和视频名称存入hashMap
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static Map<String, String> getUrlInSource(String source) throws IOException {

        Map<String, String> hashMap = new HashMap<>();
        //自由设置爬取数量
        for (int index = 300; index <= 300; index++) { // 页数最大为50，自己玩嘛，就只爬取了一页。
            //376-2-1.html
            String pageUrl = source + index + "-2-1.html";
            URL url = new URL(pageUrl);
            InputStream is = url.openStream();

            //若遇到反爬机制则使用该方法将程序伪装为浏览器进行访问
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            //BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String info = null;
            String title = null;
            // 此处不要用==null进行判断，因为网页中有很多行都是null，否则会报java.lang.NullPointerException。
            //通过观察页面不超过220行
            for (int i = 0; i < 220; i++) {
                info = br.readLine();
                //System.out.println(info);
                if (null != info) {
                    //正则取com开头,.m3u8结尾的字符串
                    String urlRegex = "(?<=com).*?(?=\\.m3u8)";
                    //System.out.println(urlRegex);

                    if (info.contains("不能观看请检查修复")) {
                        title = info;
                    }

                    Pattern pattern = Pattern.compile(urlRegex);
                    Matcher matcher = pattern.matcher(info);
                    if (matcher.find()) {
                        for (int j = 0; j <= matcher.groupCount(); j++) {
                            //去除所有的反斜杠
                            String tmp = matcher.group(j).replaceAll("\\\\", "");
                            String videoTitle = getTitle(title.trim());
                            hashMap.put(videoTitle, tmp);
                        }
                    }
                }
            }
        }
        return hashMap;
    }
    /**
     * 视频片段合成
     *
     * @param fileOutPath
     * @param keyFileMap
     */
    public static void composeFile(String fileOutPath, HashMap<Integer, String> keyFileMap) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
            byte[] bytes = new byte[1024];
            int length = 0;
            for (int i = 0; i < keyFileMap.size(); i++) {
                String nodePath = keyFileMap.get(i);
                File file = new File(nodePath);
                if (!file.exists())
                    continue;
                FileInputStream fis = new FileInputStream(file);
                while ((length = fis.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                }
            }
        } catch (Exception e) {

        }
    }



    /**
     * 清洗整理titile字符串，
     *
     * @param info
     * @return 视频标题
     */
    private static String getTitle(String info) {

        int len = info.length();
//<a href="javascript:;" onclick="MAC.Gbook.Report('编号【63254】名称【EQ-275 请干我的妻子 被丈夫之外的男人抱的贞操妻[中文字幕]】" +
//"不能观看请检查修复，页面地址' + location.href)">【报错】</a>
        //根据标题字段位置计算截取位置
        String title = info.substring(50, len - 42);
        return title;
    }

    /**
     * 解析缩影
     * @param urlpath
     * @return
     */
    public static String getIndexFile(String urlpath) {
        try {
            URL url = new URL(urlpath);
            //下在资源
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String content = "";
            String line;
            while ((line = in.readLine()) != null) {
                content += line + "\n";
            }
            in.close();
            System.out.println(content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载索引
     * @param content
     * @return
     */
    public static List analysisIndex(String content) {
        Pattern pattern = Pattern.compile(".*ts");
        Matcher ma = pattern.matcher(content);

        List<String> list = new ArrayList<String>();

        while (ma.find()) {
            String s = ma.group();
            list.add(s);
            System.out.println(s);
        }
        return list;
    }
}

