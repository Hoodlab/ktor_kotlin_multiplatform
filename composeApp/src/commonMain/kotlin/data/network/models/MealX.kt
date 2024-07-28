package data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealX(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strArea")
    val strArea: String,
    @SerialName("strInstructions")
    val strInstructions: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String,

)