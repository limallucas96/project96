package com.limallucas96.data_model.payloads

import java.io.Serializable

data class CatPayload(
    val breeds: List<BreedPayload>? = null,
    val id: String? = null,
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
) : Serializable

data class BreedPayload (
    val weight: WeightPayload? = null,
    val id: String? = null,
    val name: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val indoor: Int? = null,
    val lap: Int? = null,
    val adaptability: Int? = null,
    val intelligence: Int? = null,
    val vocalisation: Int? = null,
    val experimental: Int? = null,
    val hairless: Int? = null,
    val natural: Int? = null,
    val rare: Int? = null,
    val rex: Int? = null,
    val hypoallergenic: Int? = null,
    val grooming: Int? = null,
    val cfa_url: String? = null,
    val vetstreet_url: String? = null,
    val vcahospitals_url: String? = null,
    val country_codes: String? = null,
    val country_code: String? = null,
    val life_span: String? = null,
    val alt_names: String? = null,
    val affection_level: Int? = null,
    val child_friendly: Int? = null,
    val dog_friendly: Int? = null,
    val energy_level: Int? = null,
    val health_issues: Int? = null,
    val shedding_level: Int? = null,
    val social_needs: Int? = null,
    val stranger_friendly: Int? = null,
    val suppressed_tail: Int? = null,
    val short_legs: Int? = null,
    val wikipedia_url: String? = null,
    val reference_image_id: String? = null
) : Serializable

data class WeightPayload (
    val imperial: String? = null,
    val metric: String? = null
) : Serializable