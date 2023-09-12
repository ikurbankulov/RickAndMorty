package com.example.data.network.mapper

import com.example.data.local.models.CharacterDbModel
import com.example.data.local.models.LocationDbModel
import com.example.data.network.models.CharacterDto
import com.example.data.network.models.LocationDto
import com.example.domain.models.Character
import com.example.domain.models.Location

class Mapper {

    fun mapListDbModelToListDomain(list: List<CharacterDbModel>) = list.map {
        mapFromDbModel(it)
    }

    fun mapListDtoToListDomain(list: List<CharacterDto>) = list.map {
        mapFromDto(it)
    }

    fun mapFromDto(type: CharacterDto): Character {
        return Character(
            id = type.id,
            name = type.name,
            status = type.status,
            image = type.image,
            gender = type.gender,
            location = type.location.mapToLocation(),
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
            location = type.location.mapToLocationDto(),
            species = type.species,
            type = type.type,
            url = type.url,
        )
    }

    private fun LocationDto.mapToLocation(): Location {
        return Location(
            name = name,
            url = url
        )
    }

    private fun Location.mapToLocationDto(): LocationDto {
        return LocationDto(
            name = name,
            url = url
        )
    }

  private fun mapFromDbModel(dbModel: CharacterDbModel): Character {
        return Character(
            id = dbModel.id,
            name = dbModel.name,
            status = dbModel.status,
            image = dbModel.image,
            gender = dbModel.gender,
            location = mapLocationFromDbModel(dbModel.location),
            species = dbModel.species,
            type = dbModel.type,
            url = dbModel.url
        )
    }

    fun mapToDbModel(character: Character): CharacterDbModel {
        return CharacterDbModel(
            id = character.id,
            name = character.name,
            status = character.status,
            image = character.image,
            gender = character.gender,
            location = mapLocationToDbModel(character.location),
            species = character.species,
            type = character.type,
            url = character.url
        )
    }

    private fun mapLocationFromDbModel(dbModel: LocationDbModel): Location {
        return Location(
            name = dbModel.name,
            url = dbModel.url
        )
    }

    private fun mapLocationToDbModel(location: Location): LocationDbModel {
        return LocationDbModel(
            name = location.name,
            url = location.url
        )
    }


}



