package com.facotr.video.gitdemos

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSeekBar
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler
import com.github.hiteshsondhi88.libffmpeg.FFmpeg
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException
import com.guoxiaoxing.phoenix.compress.video.VideoCompressor
import com.guoxiaoxing.phoenix.compress.video.format.MediaFormatStrategyPresets
import com.hw.videoprocessor.VideoProcessor
import com.hw.videoprocessor.util.VideoProgressListener
import com.iceteck.silicompressorr.SiliCompressor
import mabeijianxi.camera.MediaRecorderActivity
import mabeijianxi.camera.VCamera
import mabeijianxi.camera.model.AutoVBRMode
import mabeijianxi.camera.model.LocalMediaConfig
import mabeijianxi.camera.model.MediaRecorderConfig
import mabeijianxi.camera.util.DeviceUtils
import java.io.File
import java.io.IOException
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    private var numbesfsdfr = 0
    lateinit var text: TextView;

    lateinit  var compressFile: File
    private lateinit var viewModel: MyViewModel
     lateinit  var ffmpeg: FFmpeg
    var TAG: String  = "FFMPEG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var btns = findViewById<Button>(R.id.button)
//        var btn = findViewById<Button>(R.id.text)
        text = findViewById<TextView>(R.id.txt_value)
        var btn = findViewById<Button>(R.id.button2);
        btn.setOnClickListener {
//            goToCamera();
//            compressVideo(this)
//
            val filePath = Environment.getExternalStorageDirectory()
                .absolutePath + "/" + "input.mp4"
            comPressVideoThree(this,filePath)

//            comPressFour()

//            testCompressDialog()
        }
        var seek = findViewById<AppCompatSeekBar>(R.id.seek)
        seek.max = 100
        seek.progress = 100
        test()
//        initSmallVideo(applicationContext)
        loadFFMpegBinary()


    }

    fun comPressFour(){
        val filePath = Environment.getExternalStorageDirectory()
            .absolutePath + "/" + "input.mp4"
        val outPutFile = Environment.getExternalStorageDirectory()
            .absolutePath + "/" + "aa.mp4"
        VideoProcessor.processor(this)
            .input(filePath) // .input(inputVideoUri)
            .output(outPutFile)
            //以下参数全部为可选
//            .outWidth(width)
//            .outHeight(height)
//            .startTimeMs(startTimeMs)//用于剪辑视频
//            .endTimeMs(endTimeMs)    //用于剪辑视频
//            .speed(speed)            //改变视频速率，用于快慢放
//            .changeAudioSpeed(changeAudioSpeed) //改变视频速率时，音频是否同步变化
//            .bitrate(bitrate)       //输出视频比特率
//            .frameRate(frameRate)   //帧率
//            .iFrameInterval(iFrameInterval)  //关键帧距，为0时可输出全关键帧视频（部分机器上需为-1）
            .progressListener(VideoProgressListener {

            })      //可输出视频处理进度
            .process();
    }

    //So a compress dialog  and then so a junk foods
    fun testCompressDialog(){
        var filePath = Environment.getExternalStorageDirectory().absolutePath + "input.mp4";
        var outputPath = Environment.getExternalStorageDirectory().absolutePath + "inputs.mp4";
//        val text = "ffmpeg -y -i /storage/emulated/0/1/input.mp4 -vf boxblur=25:5 -preset superfast /storage/emulated/0/1/result.mp4"
        val text = "ffmpeg -y -i " + filePath  +  " -vf boxblur=25:5 -preset superfast " + outputPath
        val split = text.split(" ")

        execFFmpegBinary(split.toTypedArray());

    }

    private fun loadFFMpegBinary() {

        try {

            ffmpeg = FFmpeg.getInstance(this.applicationContext)
            ffmpeg.loadBinary(object : LoadBinaryResponseHandler() {
                override fun onFailure() {
                     Log.e(TAG,"ffmpeg is start failure")
//                    showUnsupportedExceptionDialog()
                }
            })
        } catch (e: FFmpegNotSupportedException) {
//            showUnsupportedExceptionDialog()
        }
    }

    private fun execFFmpegBinary(command: Array<String>) {
        try {
            ffmpeg.execute(command, object : ExecuteBinaryResponseHandler() {
               override  fun onFailure(s: String) {
                   Log.e(TAG, "Started failure : ffmpeg" + s)
                }

                override fun onSuccess(s: String) {
                    Log.e(TAG, "Started success : ffmpeg $s")
//                    addTextViewToLayout("SUCCESS with output : $s")
                }

                override  fun onProgress(s: String) {
                    Log.e(TAG, "Started command : ffmpeg $command")
//                    addTextViewToLayout("progress : $s")
//                    progressDialog.setMessage("Processing\n$s")
                }

                override fun onStart() {
//                    outputLayout.removeAllViews()
                    Log.e(TAG, "Started command : ffmpeg $command")
//                    progressDialog.setMessage("Processing...")
//                    progressDialog.show()
                }

                override fun onFinish() {
                    Log.e(TAG, "Finished command : ffmpeg $command")
//                    progressDialog.dismiss()
                }
            })
        } catch (e: FFmpegCommandAlreadyRunningException) {
            // do nothing for now
        }
    }


    var listeners: VideoCompressor.Listener = object  : VideoCompressor.Listener{
        override fun onTranscodeCompleted() {
            val compressPath = compressFile.absolutePath
            Log.e("ffmpegs: ","progres complete" +  compressPath);
        }

        override fun onTranscodeProgress(progress: Double) {
             Log.e("ffmpegs: ","progres"  + progress);
        }

        override fun onTranscodeCanceled() {
        }

        override fun onTranscodeFailed(exception: java.lang.Exception?) {
            Log.e("ffmpegs: ","exception"  + exception?.message);
        }

    }

    fun comPressVideoThree(mContext: Context,localPath: String){
//        val compressFile: File
        compressFile = try {
            val compressCachePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                "phoenix"
            )
            compressCachePath.mkdir()
            File.createTempFile("compress", ".mp4", compressCachePath)
        } catch (e: IOException) {
            Toast.makeText(mContext, "Failed to create temporary file.", Toast.LENGTH_LONG).show()
            return
        }

        try {
            VideoCompressor.with().asyncTranscodeVideo(
                localPath, compressFile.absolutePath,
                MediaFormatStrategyPresets.createAndroid480pFormatStrategy(), listeners
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun compressVideo(context: Context?) {
        val filePath = Environment.getExternalStorageDirectory()
            .absolutePath + "/" + "input.mp4"
        val outPutFile = Environment.getExternalStorageDirectory()
            .absolutePath + "/" + "aaaaa"
        if (!File(outPutFile).exists()){
             File(outPutFile).createNewFile();
        }
//        try {
//            val newVideoPath = SiliCompressor.with(context).compressVideo(
//                filePath, File(outPutFile).path,
//                960, 544, 1000000
//            )
//        } catch (e: URISyntaxException) {
//            e.printStackTrace()
//        }

        try {
            val newVideoPath = SiliCompressor.with(context).compressVideo(
                filePath,
                File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "TAG"
                ).path,
                960, 544, 1000000
            )
            Log.e("sili","newVideoPath: " + newVideoPath);
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun goToCamera() {
        TODO("Not yet implemented")

        var  config =  MediaRecorderConfig.Buidler()
            .doH264Compress( AutoVBRMode()
//                        .setVelocity(BaseMediaBitrateConfig.Velocity.ULTRAFAST)
            )
            .setMediaBitrateConfig( AutoVBRMode()
//                        .setVelocity(BaseMediaBitrateConfig.Velocity.ULTRAFAST)
            )
            .smallVideoWidth(480)
            .smallVideoHeight(360)
            .recordTimeMax(6 * 1000)
            .maxFrameRate(20)
            .captureThumbnailsTime(1)
            .recordTimeMin(((1.5 * 1000).toInt()))
            .build();
        MediaRecorderActivity.goSmallVideoRecorder(this, MainActivity::class.simpleName, config);
// 选择本地视频压缩

    }

    fun localVideoCompress(){
         var path: String = ""
        var  buidler =  LocalMediaConfig.Buidler();
        var configs = buidler
            .setVideoPath(path)
            .captureThumbnailsTime(1)
            .doH264Compress( AutoVBRMode())
            .setFramerate(15)
            .setScale(1.0f)
            .build();
//        var  onlyCompressOverBean =  LocalMediaCompress(config).startCompress();
    }

    //VCamera.setVideoCachePath
    fun initSmallVideo(context: Context?) {
        // 设置拍摄视频缓存路径
        val dcim: File = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                VCamera.setVideoCachePath(dcim.toString() + "/mabeijianxi/")
            } else {
                VCamera.setVideoCachePath(
                    dcim.getPath().replace(
                        "/sdcard/",
                        "/sdcard-ext/"
                    )
                        .toString() + "/mabeijianxi/"
                )
            }
        } else {
            VCamera.setVideoCachePath(dcim.toString() + "/mabeijianxi/")
        }
        VCamera.setDebugMode(true)
        VCamera.initialize(context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt("NUM",number)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    //So a junk foods //So a junk foods you can names and then so a junk foods
    //So a junk foods
    fun test() {
        //创建一个SpannableString对象
        //So a junk foods you can see a goods names and then so a junk foods  and then so a junk food
        //So a goods names you can see a goods
        var sStr = SpannableString("最是那一低头的温柔，像一朵水莲花不胜凉风的娇羞，道一声珍重，道一声珍重，那一声珍重里有蜜甜的忧愁");
        //设置字体(default,default-bold,monospace,serif,sans-serif)
        sStr.setSpan(StyleSpan(Typeface.BOLD), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（绝对值,单位：像素）,第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素
        sStr.setSpan(AbsoluteSizeSpan(20), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(sStr.toString())


    }


    //So a junk foods sStr.toString
    fun testSundayName() {
        //So a junk food
        var buffer = StringBuffer();
        buffer.append("1")
            .append("2")
            .append("3")

    }

    //So a junk foods
    fun testNames(){

    }




}
