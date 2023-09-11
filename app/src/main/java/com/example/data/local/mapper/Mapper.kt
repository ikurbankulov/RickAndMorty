package com.example.data.local.mapper

import com.example.data.local.models.CharacterDbModel
import com.example.data.local.models.LocationDbModel
import com.example.domain.models.Character
import com.example.domain.models.Location

class Mapper {

    fun mapToDbModel(character: Character): CharacterDbModel {
        return CharacterDbModel(
            id = character.id,
            name = character.name,
            status = character.status,
            image = character.image,
            gender = character.gender,
            location = mapToDbModel(character.location),
            species = character.species,
            type = character.type,
            url = character.url
        )
    }

    private fun mapToDbModel(location: Location): LocationDbModel {
        return LocationDbModel(
            name = location.name,
            url = location.url
        )
    }
}