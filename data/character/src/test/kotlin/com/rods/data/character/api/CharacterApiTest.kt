package com.rods.data.character.api

import com.rods.data.character.api.model.CharactersResponse
import com.rods.data.character.api.model.Comics
import com.rods.data.test.MockWebServerResponsePath
import com.rods.data.test.MockWebServerRule
import com.rods.data.test.createTestApi
import com.rods.data.utils.model.Data
import com.rods.data.utils.model.RawResponse
import com.rods.data.utils.model.common.Events
import com.rods.data.utils.model.common.Series
import com.rods.data.utils.model.common.Stories
import com.rods.data.utils.model.common.Thumbnail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.*

@ExperimentalCoroutinesApi
class CharacterApiTest {

    @get:Rule
    val webServerRule = MockWebServerRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var apiService: CharacterApi

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        apiService = createTestApi(webServerRule.provideUrl())
    }

    @Test
    @MockWebServerResponsePath("single_character_response.json")
    fun getCharacters_returnExpectedResponse() = runBlocking {
        val response = apiService.getCharacters(10, 10, "any")
        Assert.assertEquals(response, expectedResponse)
    }

    private val expectedResponse = RawResponse(
        attributionHTML = "<a href=\"http://marvel.com\">Data provided by Marvel. © 2020 MARVEL</a>",
        attributionText="Data provided by Marvel. © 2020 MARVEL",
        code=200,
        copyright="© 2020 MARVEL",
        etag="0494cc9649cf571651f5cebdb7188987dae242de",
        status="Ok",
        data = Data(
            offset= 0,
            limit= 10,
            total= 1,
            count= 1,
            results = listOf(
                CharactersResponse(
                    id = 1009610,
                    name = "Spider-Man",
                    description = "Bitten by a radioactive spider, high school student Peter Parker gained the speed, strength and powers of a spider. Adopting the name Spider-Man, Peter hoped to start a career using his new abilities. Taught that with great power comes great responsibility, Spidey has vowed to use his powers to help people.",
                    modified = "2020-07-21T10:30:10-0400",
                    thumbnail = Thumbnail(
                        "jpg",
                        "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b"
                    ),
                    resourceURI = "http://gateway.marvel.com/v1/public/characters/1009610",
                    comics = Comics(
                        available = 3954,
                        collectionURI = "http://gateway.marvel.com/v1/public/characters/1009610/comics",
                        items = emptyList(),
                        returned = 20
                    ),
                    series = Series(
                        available = 1004,
                        collectionURI = "http://gateway.marvel.com/v1/public/characters/1009610/series",
                        items = emptyList(),
                        returned = 20
                    ),
                    stories = Stories(
                        available = 5940,
                        collectionURI = "http://gateway.marvel.com/v1/public/characters/1009610/stories",
                        items = emptyList(),
                        returned = 20
                    ),
                    events = Events(
                        available = 38,
                        collectionURI = "http://gateway.marvel.com/v1/public/characters/1009610/events",
                        items = emptyList(),
                        returned = 20
                    ),
                    urls = emptyList()
                )
            )
        )
    )

}