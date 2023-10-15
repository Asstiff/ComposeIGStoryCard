package com.mastiff.storydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mastiff.storydemo.ui.theme.StoryDemoTheme
import com.mastiff.storydemo.ui.widgets.StoryCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.enableEdgeToEdge()
            StoryDemoTheme {
                Surface(
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(24.dp)) {
                        StoryCard(
                            modifier = Modifier
                                .height(240.dp)
                                .fillMaxWidth(),
                            images = arrayOf(
                                "https://img95.699pic.com/photo/50080/9588.jpg_wh300.jpg",
                                "https://img95.699pic.com/photo/50034/0209.jpg_wh300.jpg",
                                "https://static.runoob.com/images/demo/demo3.jpg",
                                "https://img95.699pic.com/photo/50046/5562.jpg_wh300.jpg",
                                "https://picx.zhimg.com/v2-3b4fc7e3a1195a081d0259246c38debc_720w.jpg?source=172ae18b",
                                "https://pic.52112.com/2020/04/13/JPG-200413_328/gCaPae4zjp_small.jpg",
                                "https://static.runoob.com/images/demo/demo2.jpg",
                                "https://p2.img.cctvpic.com/photoAlbum/page/performance/img/2022/4/1/1648804913581_50.jpg",
                            )
                        )
                    }
                }
            }
        }
    }
}