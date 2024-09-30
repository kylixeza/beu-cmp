package beukmm.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Poppins_Bold
import beukmm.common.generated.resources.Poppins_Light
import beukmm.common.generated.resources.Poppins_Regular
import beukmm.common.generated.resources.Poppins_SemiBold
import beukmm.common.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFamily() = FontFamily(
        Font(Res.font.Poppins_Light, weight = FontWeight.Light, style = FontStyle.Normal),
        Font(Res.font.Poppins_Regular, weight = FontWeight.Normal, style = FontStyle.Normal),
        Font(Res.font.Poppins_SemiBold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
        Font(Res.font.Poppins_Bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    )


@Composable
fun BeuTypography() = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),

    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Neutral900,
        fontFamily = PoppinsFamily(),
    ),
)