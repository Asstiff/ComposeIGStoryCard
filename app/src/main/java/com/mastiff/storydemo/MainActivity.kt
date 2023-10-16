package com.mastiff.storydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        StoryCard(
                            modifier = Modifier
                                .height(540.dp)
                                .fillMaxWidth(),
                            images = arrayOf(
                                "https://images.pexels.com/photos/3565742/pexels-photo-3565742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3052361/pexels-photo-3052361.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/1181340/pexels-photo-1181340.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3538659/pexels-photo-3538659.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3717270/pexels-photo-3717270.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3538721/pexels-photo-3538721.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3670681/pexels-photo-3670681.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                "https://images.pexels.com/photos/3565746/pexels-photo-3565746.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                            )
                        )
                    }
                }
            }
        }
    }
}