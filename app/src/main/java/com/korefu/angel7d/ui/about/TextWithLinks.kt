package com.korefu.angel7d.ui.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString

@Composable
fun TextWithLinks(annotatedText: AnnotatedString, modifier: Modifier) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    ClickableText(
        modifier = modifier,
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                "URL",
                start = offset,
                end = offset
            ).firstOrNull()
                ?.let { annotation -> uriHandler.openUri(annotation.item) }
            annotatedText.getStringAnnotations(
                "MAIL",
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                val intent = Intent(Intent.ACTION_VIEW)
                val data: Uri =
                    Uri.parse("mailto:${annotation.item}?subject=" + "" + "&body=" + "")
                intent.data = data
                context.startActivity(intent)
            }
            annotatedText.getStringAnnotations(
                "PHONE",
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${annotation.item}")
                context.startActivity(intent)
            }
        })
}