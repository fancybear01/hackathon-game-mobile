package com.coding.main_screen_impl.main_screen.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.coding.main_screen_impl.main_screen.compose.components.ActionButtons
import com.coding.main_screen_impl.main_screen.compose.components.AppDimens
import com.coding.main_screen_impl.main_screen.compose.components.DrawerMenu
import com.coding.main_screen_impl.main_screen.compose.components.MainTopBar
import com.coding.main_screen_impl.main_screen.compose.components.PetInfo
import com.coding.main_screen_impl.main_screen.compose.components.PrizeItem


// ---------- MainScreenContent ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    petName: String,
    status: String,
    progress: Float = 0.6f, // прогресс (например 60%), будет приходить из БД
    onCourseClick: () -> Unit = {},
    onPrizesClick: () -> Unit = {},
    onRewardsClick: () -> Unit = {}
) {
    var menuVisible by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showPrizesSheet by remember { mutableStateOf(false) }

    // Вызываем onPrizesClick, чтобы показать sheet
    if (showPrizesSheet) {
        onPrizesClick()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient( // линейный градиент
                    colors = listOf(Color(0xFF1919EF), Color(0xFF8694E4)),
                )
            )
    ) {
        // TopBar
        MainTopBar {
            menuVisible = !menuVisible
        }

        // Drawer под TopAppBar
        AnimatedVisibility(visible = menuVisible) {
            DrawerMenu { item ->
                menuVisible = false
                when (item) {
                    "Награды" -> onRewardsClick()
                    else -> Unit
                }
            }
        }

        // Контент
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppDimens.Padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PetInfo(petName, status, progress)
            ActionButtons(
                onCourseClick = onCourseClick,
                onPrizesClick = { showPrizesSheet = true }
            )
        }

        // Modal Bottom Sheet для призов
        if (showPrizesSheet) {
            ModalBottomSheet(
                onDismissRequest = { showPrizesSheet = false },
                sheetState = sheetState,
                containerColor = Color.Transparent,
                contentColor = Color(0xFF1919EF),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                dragHandle = null
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF58FFFF), Color.White)
                            ),
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Ежедневные призы", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))
                        PrizeItem("Кэшбэк", "Кэшбэк 5% на все покупки")
                        PrizeItem("Промокод", "Промокод на скидку 10%")
                        PrizeItem("Кепка", "Брендированная кепка с логотипом")
                    }
                }
            }
        }
    }
}
