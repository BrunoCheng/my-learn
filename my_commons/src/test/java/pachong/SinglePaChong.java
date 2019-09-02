package pachong;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @Date 2019/8/30
 * @Created by acheng
 */
public class SinglePaChong {

    private static String rootPath = "D:\\pc";

    public static void main(String[] args) {
        String indexPath = "https://ap1-ws.yoipu.com/9DeBgxlr84n/preview/pv.m3u8";
        String prePath = indexPath.substring(0,indexPath.lastIndexOf("/")+1);
        System.out.println(prePath);
        //下载索引文件
        String indexStr = getIndexFile(indexPath);
        System.out.println(indexStr);
        //解析索引文件
        List videoUrlList = analysisIndex(indexStr);
        //下载视频片段
        List<String> fileList = downLoadIndexFile(prePath,videoUrlList);
    }




    public static String getIndexFile(String urlpath){
        try{
            URL url = new URL(urlpath);
            //下载资源
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            String content = "" ;
            String line;
            while ((line = in.readLine()) != null) {
                content += line + "\n";
            }
            in.close();
            System.out.println(content);
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List analysisIndex(String content){
        Pattern pattern = Pattern.compile(".*ts");
        Matcher ma = pattern.matcher(content);

        List<String> list = new ArrayList<String>();

        while(ma.find()){
            String s = ma.group();
            list.add(s);
            //System.out.println(s);
        }
        return list;
    }
    public static List<String> downLoadIndexFile(String preUrlPath,List<String> urlList){
        try{
            List<String> filePathList = new ArrayList<String>();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            for(String urlpath:urlList){
                URL url = new URL(preUrlPath+urlpath);
                //下在资源
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String fileOutPath = rootPath+File.separator+uuid+File.separator+urlpath;
                File file = new File(rootPath+File.separator+uuid);
                if(!file.exists()){
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = dataInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                }
                System.out.println("下载完成..."+fileOutPath);
                dataInputStream.close();
                filePathList.add(fileOutPath);
            }

            return  filePathList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
