package com.korefu.angel7d.data

import com.korefu.angel7d.R

enum class FormType(
    val titleId: Int,
    val shortDescriptionId: Int,
    val longDescriptionId: Int
) {
    PSYCHOLOGICAL(
        R.string.psycho_tatneft_help_title,
        R.string.psycho_help_desc_short,
        R.string.psycho_help_desc_long
    ),
    ADVICE(
        R.string.advice_help_title,
        R.string.advice_help_desc_short,
        R.string.advice_help_desc_long
    ),
    PRAYER(
        R.string.prayer_help_title,
        R.string.prayer_help_desc_short,
        R.string.prayer_help_desc_long
    ),
    FEEDBACK(
        R.string.feedback_title,
        R.string.feedback_desc,
        R.string.feedback_desc
    )
}
