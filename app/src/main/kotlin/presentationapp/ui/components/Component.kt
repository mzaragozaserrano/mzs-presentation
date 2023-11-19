package presentationapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.app.R
import com.mzaragozaserrano.presentation.compose.components.cards.RoundedCard
import com.mzaragozaserrano.presentation.compose.components.labels.WavyLabel
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import presentationapp.ui.vo.ComponentVO

@Composable
fun Component(modifier: Modifier = Modifier, component: ComponentVO) {

    RoundedCard(modifier = modifier, backgroundColor = MaterialTheme.colorScheme.surfaceVariant) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            LargeBoldText(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = stringResource(id = component.nameId).uppercase()
            )
            component.listItems.forEach { item ->
                item()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ComponentPrev() {
    Component(
        component = ComponentVO(
            nameId = R.string.wavy_label,
            listItems = listOf {
                WavyLabel(
                    buttonBackgroundColor = Color.LightGray,
                    iconBackgroundColor = Color.White,
                    iconId = R.drawable.ic_category_labels,
                    iconTint = Color.Black,
                    textColor = Color.Black,
                    textId = R.string.label_text
                )
            }
        )
    )
}