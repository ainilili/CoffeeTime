使用方法：
首先推荐看demo演示：http://cyberplayer.bcelive.com/demo/new/index.html

3.4.0更新日志如下：
1、chrome66不能自动播放导致的一个bugFix。
2、部分编码的hls视频出现跳帧的bugFix

几个注意点：
1、flv或hls视频要用h5必须支持跨域访问，即ResponseHeader中要设置Access-Control-Allow-Origin:*。
2、可以通过配置primary: 'flash'继续高优试用flash进行播放（并不推荐这么做）。
2、用H5播放hls格式视频此版本无需加载videojs文件，会自动根据系统环境来加载。
3、播放直播视频流的时候，一定要设置isLive: true。
4、在部分android手机环境的浏览器上，原生不支持播放hls。

更多帮助和API请见：https://cloud.baidu.com/doc/MCT/Web-SDK.html
