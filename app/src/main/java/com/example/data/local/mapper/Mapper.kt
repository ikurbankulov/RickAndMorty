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

    fun mapToCharacter(dbModel: CharacterDbModel): Character {
        return Character(
            id = dbModel.id,
            name = dbModel.name,
            status = dbModel.status,
            image = dbModel.image,
            gender = dbModel.gender,
            location = mapToLocation(dbModel.location),
            species = dbModel.species,
            type = dbModel.type,
            url = dbModel.url
        )
    }

    private fun mapToLocation(locationDbModel: LocationDbModel): Location {
        return Location(
            name = locationDbModel.name,
            url = locationDbModel.url
        )
    }


}