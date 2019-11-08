package com.octavianionel.openxchangetest.model

/**
 * Created by Reply on 08/11/2019.
 */
data class Earthquake(
    var uniqueId: Int? = null,
    var time: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var depth: String? = null,
    var mag: String? = null,
    var magType: String? = null,
    var nst: String? = null,
    var gap: String? = null,
    var dmin: String? = null,
    var rms: String? = null,
    var net: String? = null,
    var id: String? = null,
    var updated: String? = null,
    var place: String? = null,
    var type: String? = null,
    var horizontalError: String? = null,
    var depthError: String? = null,
    var magError: String? = null,
    var magNst: String? = null,
    var status: String? = null,
    var locationSources: String? = null,
    var magSource: String? = null
)
