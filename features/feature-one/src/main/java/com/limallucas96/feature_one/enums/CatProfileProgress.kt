package com.limallucas96.feature_one.enums

import androidx.annotation.StringRes
import com.limallucas96.feature_one.R

enum class CatProfileProgress(@StringRes val stringRes: Int, val step: Int) {
    CAT_PROFILE_STEP(R.string.cat_profile_step, 1),
    CAT_PICKER_STEP(R.string.cat_picker_step, 2),
    CAT_SUMMARY_STEP(R.string.cat_summary_step, 3),
    NONE(R.string.empty_string, 0);

    companion object {
        fun getSumOfSteps() = values().size - 1
    }
}