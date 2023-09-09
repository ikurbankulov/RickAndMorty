package com.example.data.mapper

import com.example.data.models.CharacterEntity
import com.example.data.models.LocationEntity
import com.example.domain.models.Character
import com.example.domain.models.Location

class Mapper {
    fun mapFromEntity(type: CharacterEntity): Character {
        return Character(
            id = type.id,
            name = type.name,
            status = type.status,
            image = type.image,
            created = type.created,
            gender = type.gender,
            location = mapLocationFromEntity(type.location),
            species = type.species,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }

    fun mapToEntity(type: Character): CharacterEntity {
        return CharacterEntity(
            id = type.id,
            name = type.name,
            status = type.status,
            image = type.image,
            created = type.created,
            gender = type.gender,
            location = mapLocationToEntity(type.location),
            species = type.species,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }

    private fun mapLocationFromEntity(type: LocationEntity): Location {
        return Location(
            name = type.name,
            url = type.url
        )
    }

    private fun mapLocationToEntity(type: Location): LocationEntity {
        return LocationEntity(
            name = type.name,
            url = type.url
        )
    }
}
