package com.wojdeb.citysearch.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.layoutInflater() = context.layoutInflater()

fun Context.layoutInflater() = LayoutInflater.from(this)