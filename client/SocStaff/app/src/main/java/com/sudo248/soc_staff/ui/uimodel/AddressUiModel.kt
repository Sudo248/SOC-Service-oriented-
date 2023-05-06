package com.sudo248.soc_staff.ui.uimodel

import androidx.databinding.ObservableField
import com.sudo248.soc_staff.domain.entity.user.Location

data class AddressUiModel(
    var provinceID: Int = 0,
    var districtID: Int = 0,
    var wardCode: String = "",
    val provinceName: ObservableField<String> = ObservableField(""),
    val districtName: ObservableField<String> = ObservableField(""),
    val wardName: ObservableField<String> = ObservableField(""),
    val address: ObservableField<String> = ObservableField(""),
    val location: Location = Location(),
    val fullAddress: ObservableField<String> = ObservableField(""),
)