package com.example.data.network.mapper

import com.example.data.network.models.CharacterDto
import com.example.data.network.models.LocationDto
import com.example.domain.models.Character
import com.example.domain.models.Location

class Mapper {
    fun mapFromDto(type: CharacterDto): Character {
        return Character(
            id = type.id,
            name = type.name,
            status = type.status,
            image = type.image,
            gender = type.gender,
            location = mapLocationFromDto(type.location),
            species = type.species,
            type = type.type,
            url = type.url,
        )
    }

    fun mapToDto(type: Character): CharacterDto {
        return CharacterDto(
            id = type.id,
            name = type.name,
            status = type.status,
            image = type.image,
            gender = type.gender,
            location = mapLocationToDto(type.location),
            species = type.species,
            type = type.type,
            url = type.url,
        )
    }

    private fun mapLocationFromDto(type: LocationDto): Location {
        return Location(
            name = type.name,
            url = type.url
        )
    }

    private fun mapLocationToDto(type: Location): LocationDto {
        return LocationDto(
            name = type.name,
            url = type.url
        )
    }
}
