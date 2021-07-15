package com.korefu.angel7d

enum class FormTypes(
    val titleId: Int,
    val shortDescriptionId: Int,
    val longDescriptionId: Int
) {
    PSYCHOLOGICAL(
        R.string.psycho_help_title,
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
    QUESTION(
        R.string.question_title,
        R.string.question_desc,
        R.string.question_desc
    ),
    FEEDBACK(
        R.string.feedback_title,
        R.string.feedback_desc,
        R.string.feedback_desc
    )
}