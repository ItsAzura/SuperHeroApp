package com.example.superheroapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroapplication.Data.SuperHero
import com.example.superheroapplication.Data.superheroes
import com.example.superheroapplication.ui.theme.SuperHeroApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HeroApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroApp(){
    val expandedList = remember { mutableStateListOf<Boolean>() }
    //tạo 1 danh sách có thể thay đổi để chứa các giá trị kiểu boolean
    for (i in superheroes.indices) {
        expandedList.add(false)
    }
    //Tạo 1 vòng lặp đi qua từng phần tử trong list và thêm 1 giá trị false khi đi qua 1 phần tử
    Scaffold(
        topBar = {
            HeroTopBar()
        }
    ) {
        LazyColumn(contentPadding = it) {
            itemsIndexed(superheroes) {index, superhero ->
                //Lặp qua danh sách "superheroes", index mỗi phần tử superhero trong list còn superhero là giá trị
                HeroItem(
                    hero = superhero,
                    //hiện thị thông tin
                    expanded = expandedList[index],
                    //expand dựa trên  expandedList hiện tại
                    onClick = { expandedList[index] = !expandedList[index] },
                    //khi mỗi heroitem đc click thì  expandedList hiện tại chuyển từ false
                    //Giúp có thể được mở rộng
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopBar(
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 30.dp
                    ),
                text = "SuperHero",
                color =  if (isSystemInDarkTheme()) Color(0xFFFFFFFF)
                else Color(0xFF000000),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroItem(
    hero: SuperHero,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = onClick,
        modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isSystemInDarkTheme()) Color(0xFFD6BAFF)
                        else Color(0xFF6E4EA1)
                    )
                    .padding(8.dp),
            ){

                HeroInfo(
                    heroName = hero.heroName,
                    heroDesc = hero.heroDescriptor,
                )
                Spacer(modifier = Modifier.weight(2f))
                HeroIcon(heroIcon = hero.heroPic)
            }
            if(expanded){
                HeroPower(
                    heroPower = hero.heroPower,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (isSystemInDarkTheme()) Color(0xFFD6BAFF)
                            else Color(0xFF6E4EA1)
                        )
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                )
            }
        }

    }
}
@Composable
fun HeroInfo(
    @StringRes heroName: Int,
    @StringRes heroDesc: Int,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.width(250.dp)) {
        Text(
            text = stringResource(id = heroName),
            color = if (isSystemInDarkTheme()) Color(0xFF3E1C6F)
            else Color(0xFFFFFFFF),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = heroDesc),
            color =  if (isSystemInDarkTheme()) Color(0xFF3E1C6F)
            else Color(0xFFFFFFFF),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
    }
}
@Composable
fun HeroIcon(
    @DrawableRes heroIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .width(110.dp)
            .height(120.dp)
            .padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = heroIcon),
        contentDescription =""
    )
}
@Composable
fun HeroPower(
    @StringRes heroPower :Int,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Power: ",
            color = if (isSystemInDarkTheme()) Color(0xFF3E1C6F)
            else Color(0xFFFFFFFF),
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text = stringResource(heroPower),
            color = if (isSystemInDarkTheme()) Color(0xFF3E1C6F)
            else Color(0xFFFFFFFF),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}