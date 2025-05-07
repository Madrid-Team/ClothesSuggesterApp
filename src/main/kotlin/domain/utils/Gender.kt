package domain.utils

import domain.utils.exceptions.ClothesException

enum class Gender {
    MALE,
    FEMALE;

    companion object {
        fun fromString(value: String): Gender {

            return when (value.lowercase()) {
                "male" -> MALE
                "female" -> FEMALE
                else -> throw ClothesException.UnknownGenderException("Unknown gender '$value'")
            }

        }
    }
}