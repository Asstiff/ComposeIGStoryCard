package com.mastiff.storydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mastiff.storydemo.classes.Image
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        StoryCard(
                            modifier = Modifier
                                .height(440.dp)
                                .fillMaxWidth(),
                            images = arrayOf(
                                Image("https://images.pexels.com/photos/3565742/pexels-photo-3565742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3565742/pexels-photo-3565742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff", "Snow House", "这是一个被雪覆盖的小屋，在冬季的寒风和白雪中显得尤为温暖和宁静。人们在这里度过一个平和的冬夜，享受着火炉和热茶带来的温暖。"),
                                Image("https://images.pexels.com/photos/3052361/pexels-photo-3052361.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3052361/pexels-photo-3052361.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff1", "City Night", "这张图片展示了城市夜晚的霓虹灯和繁忙的街道，人们在夜幕下寻找娱乐和忙碌，形成一道美丽的风景线。"),
                                Image("https://images.pexels.com/photos/1181340/pexels-photo-1181340.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/1181340/pexels-photo-1181340.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff2", "棕榈树", "这是一片棕榈树环绕的海滩，阳光透过树叶洒在沙滩上，给人一种度假和放松的感觉，仿佛置身于一个热带天堂。"),
                                Image("https://images.pexels.com/photos/3538659/pexels-photo-3538659.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3538659/pexels-photo-3538659.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff3", "Snowy Forest", "这是一个被大雪覆盖的森林，树木和地面都披上了一层厚厚的白雪，显得幽静而神秘，像是一个白色的梦境。"),
                                Image("https://images.pexels.com/photos/3717270/pexels-photo-3717270.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3717270/pexels-photo-3717270.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff4", "Bike", "这是一辆停在街头的摩托车，它象征着简单和自由，让人想起年轻时无忧无虑的骑行时光。"),
                                Image("https://images.pexels.com/photos/3538721/pexels-photo-3538721.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3538721/pexels-photo-3538721.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff5", "Mountain", "这是一座巍峨的山峰，矗立在蓝天白云下，给人一种挑战和探险的冲动，仿佛在召唤人们去征服它。"),
                                Image("https://images.pexels.com/photos/3670681/pexels-photo-3670681.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3670681/pexels-photo-3670681.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff6", "Woods", "这是一片郁郁葱葱的树林，其中隐藏着无数生灵和未知，给人一种探险和神秘的感觉。"),
                                Image("https://images.pexels.com/photos/3565746/pexels-photo-3565746.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "https://images.pexels.com/photos/3565746/pexels-photo-3565746.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", "Mastiff7", "Lake", "这是一个宁静的湖泊，水面平静如镜，倒映着周围的自然美景，给人一种宁静和平和的感觉。")
                            )
                        )
                    }
                }
            }
        }
    }
}