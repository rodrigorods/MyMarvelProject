package com.rods.domain.character.model

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    var favorited: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if (other !is MarvelCharacter) false
        else (other.id == this.id &&
                other.name == this.name &&
                other.description == this.description)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + thumbnailUrl.hashCode()
        result = 31 * result + favorited.hashCode()
        return result
    }
}