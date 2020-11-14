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
    fun response() = runBlocking {
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

    //RawResponse(attributionHTML=<a href="http://marvel.com">Data provided by Marvel. © 2020 MARVEL</a>, attributionText=Data provided by Marvel. © 2020 MARVEL, code=200, copyright=© 2020 MARVEL, data=Data(count=1, limit=10, offset=0, results=[CharactersResponse(comics=Comics(available=3954, collectionURI=http://gateway.marvel.com/v1/public/characters/1009610/comics, items=[Item(name=Spider-Man: 101 Ways to End the Clone Saga (1997) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/62304, type=null), Item(name=2099 Alpha (2019) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/78503, type=null), Item(name=A YEAR OF MARVELS TPB (Trade Paperback), resourceURI=http://gateway.marvel.com/v1/public/comics/60151, type=null), Item(name=A+X (2012) #4, resourceURI=http://gateway.marvel.com/v1/public/comics/43501, type=null), Item(name=Absolute Carnage (2019) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/76012, type=null), Item(name=Absolute Carnage (2019) #2, resourceURI=http://gateway.marvel.com/v1/public/comics/76014, type=null), Item(name=Absolute Carnage (2019) #5, resourceURI=http://gateway.marvel.com/v1/public/comics/78960, type=null), Item(name=Absolute Carnage: Symbiote Spider-Man (2019) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/77071, type=null), Item(name=Actor Presents Spider-Man and the Incredible Hulk (2003) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/320, type=null), Item(name=Adventures of Spider-Man (1996) #1, resourceURI=http://gateway.marvel.com/v1/public/comics/76863, type=null), Item(name=Adventures of Spider-Man (1996) #2, resourceURI=http://gateway.marvel.com/v1/public/comics/76864, type=null), Item(name=Adventures of Spider-Man (1996) #3, resourceURI=http://gateway.marvel.com/v1/public/comics/76865, type=null), Item(name=Adventures of Spider-Man (1996) #4, resourceURI=http://gateway.marvel.com/v1/public/comics/76866, type=null), Item(name=Adventures of Spider-Man (1996) #5, resourceURI=http://gateway.marvel.com/v1/public/comics/76867, type=null), Item(name=Adventures of Spider-Man (1996) #6, resourceURI=http://gateway.marvel.com/v1/public/comics/76868, type=null), Item(name=Adventures of Spider-Man (1996) #7, resourceURI=http://gateway.marvel.com/v1/public/comics/76869, type=null), Item(name=Adventures of Spider-Man (1996) #8, resourceURI=http://gateway.marvel.com/v1/public/comics/76870, type=null), Item(name=Adventures of Spider-Man (1996) #9, resourceURI=http://gateway.marvel.com/v1/public/comics/76871, type=null), Item(name=Adventures of Spider-Man (1996) #10, resourceURI=http://gateway.marvel.com/v1/public/comics/76872, type=null), Item(name=Adventures of Spider-Man (1996) #11, resourceURI=http://gateway.marvel.com/v1/public/comics/76873, type=null)], returned=20), description=Bitten by a radioactive spider, high school student Peter Parker gained the speed, strength and powers of a spider. Adopting the name Spider-Man, Peter hoped to start a career using his new abilities. Taught that with great power comes great responsibility, Spidey has vowed to use his powers to help people., events=Events(available=38, collectionURI=http://gateway.marvel.com/v1/public/characters/1009610/events, items=[Item(name=Acts of Vengeance!, resourceURI=http://gateway.marvel.com/v1/public/events/116, type=null), Item(name=Age of Ultron, resourceURI=http://gateway.marvel.com/v1/public/events/314, type=null), Item(name=Atlantis Attacks, resourceURI=http://gateway.marvel.com/v1/public/events/233, type=null), Item(name=Avengers Disassembled, resourceURI=http://gateway.marvel.com/v1/public/events/234, type=null), Item(name=Avengers VS X-Men, resourceURI=http://gateway.marvel.com/v1/public/events/310, type=null), Item(name=Chaos War, resourceURI=http://gateway.marvel.com/v1/public/events/296, type=null), Item(name=Civil War, resourceURI=http://gateway.marvel.com/v1/public/events/238, type=null), Item(name=Dark Reign, resourceURI=http://gateway.marvel.com/v1/public/events/318, type=null), Item(name=Days of Future Present, resourceURI=http://gateway.marvel.com/v1/public/events/240, type=null), Item(name=Dead No More: The Clone Conspiracy, resourceURI=http://gateway.marvel.com/v1/public/events/332, type=null), Item(name=Enemy of the State, resourceURI=http://gateway.marvel.com/v1/public/events/245, type=null), Item(name=Evolutionary War, resourceURI=http://gateway.marvel.com/v1/public/events/246, type=null), Item(name=Fear Itself, resourceURI=http://gateway.marvel.com/v1/public/events/302, type=null), Item(name=House of M, resourceURI=http://gateway.marvel.com/v1/public/events/251, type=null), Item(name=Inferno, resourceURI=http://gateway.marvel.com/v1/public/events/252, type=null), Item(name=Infinity, resourceURI=http://gateway.marvel.com/v1/public/events/315, type=null), Item(name=Inhumanity, resourceURI=http://gateway.marvel.com/v1/public/events/317, type=null), Item(name=Initiative, resourceURI=http://gateway.marvel.com/v1/public/events/255, type=null), Item(name=Kraven's Last Hunt, resourceURI=http://gateway.marvel.com/v1/public/events/258, type=null), Item(name=Maximum Carnage, resourceURI=http://gateway.marvel.com/v1/public/events/151, type=null)], returned=20), id=1009610, modified=2020-07-21T10:30:10-0400, name=Spider-Man, resourceURI=http://gateway.marvel.com/v1/public/characters/1009610, series=Series(available=1004, collectionURI=http://gateway.marvel.com/v1/public/characters/1009610/series, items=[Item(name= Superior Spider-Man Vol. 2: Otto-matic (2019), resourceURI=http://gateway.marvel.com/v1/public/series/26024, type=null), Item(name=2099 Alpha (2019), resourceURI=http://gateway.marvel.com/v1/public/series/27980, type=null), Item(name=A YEAR OF MARVELS TPB (2017), resourceURI=http://gateway.marvel.com/v1/public/series/22102, type=null), Item(name=A+X (2012 - 2014), resourceURI=http://gateway.marvel.com/v1/public/series/16450, type=null), Item(name=Absolute Carnage (2019 - Present), resourceURI=http://gateway.marvel.com/v1/public/series/27272, type=null), Item(name=Absolute Carnage: Symbiote Spider-Man (2019), resourceURI=http://gateway.marvel.com/v1/public/series/27637, type=null), Item(name=Actor Presents Spider-Man and the Incredible Hulk (2003), resourceURI=http://gateway.marvel.com/v1/public/series/458, type=null), Item(name=Adventures of Spider-Man (1996 - 1997), resourceURI=http://gateway.marvel.com/v1/public/series/27587, type=null), Item(name=Adventures Of Spider-Man: Sinister Intentions (2019), resourceURI=http://gateway.marvel.com/v1/public/series/27025, type=null), Item(name=Adventures Of Spider-man: Spectacular Foes (2019), resourceURI=http://gateway.marvel.com/v1/public/series/27671, type=null), Item(name=Age of Heroes (2010), resourceURI=http://gateway.marvel.com/v1/public/series/9790, type=null), Item(name=AGE OF HEROES TPB (2011), resourceURI=http://gateway.marvel.com/v1/public/series/10235, type=null), Item(name=Age of Ultron (2013), resourceURI=http://gateway.marvel.com/v1/public/series/17318, type=null), Item(name=Agents of Atlas: The Complete Collection Vol. 1 (2018), resourceURI=http://gateway.marvel.com/v1/public/series/24134, type=null), Item(name=Alias (2001 - 2003), resourceURI=http://gateway.marvel.com/v1/public/series/672, type=null), Item(name=Alias Omnibus (2006), resourceURI=http://gateway.marvel.com/v1/public/series/13383, type=null), Item(name=Alpha Flight (1983 - 1994), resourceURI=http://gateway.marvel.com/v1/public/series/2116, type=null), Item(name=Alpha: Big Time (2013), resourceURI=http://gateway.marvel.com/v1/public/series/17650, type=null), Item(name=Amazing Fantasy (1962), resourceURI=http://gateway.marvel.com/v1/public/series/2987, type=null), Item(name=Amazing Fantasy (2004 - 2006), resourceURI=http://gateway.marvel.com/v1/public/series/769, type=null)], returned=20), stories=Stories(available=5940, collectionURI=http://gateway.marvel.com/v1/public/characters/1009610/stories, items=[Item(name=Interior #483, resourceURI=http://gateway.marvel.com/v1/public/stories/483, type=interiorStory), Item(name=Cover #486, resourceURI=http://gateway.marvel.com/v1/public/stories/486, type=cover), Item(name=Interior #487, resourceURI=http://gateway.marvel.com/v1/public/stories/487, type=interiorStory), Item(name=SENSATIONAL SPIDER-MAN (2006) #23, resourceURI=http://gateway.marvel.com/v1/public/stories/498, type=cover), Item(name=Interior #499, resourceURI=http://gateway.marvel.com/v1/public/stories/499, type=interiorStory), Item(name=Cover #598, resourceURI=http://gateway.marvel.com/v1/public/stories/598, type=cover), Item(name=Interior #599, resourceURI=http://gateway.marvel.com/v1/public/stories/599, type=interiorStory), Item(name=Interior #805, resourceURI=http://gateway.marvel.com/v1/public/stories/805, type=interiorStory), Item(name=Cover #824, resourceURI=http://gateway.marvel.com/v1/public/stories/824, type=cover), Item(name=3 of 3 - Family Business, resourceURI=http://gateway.marvel.com/v1/public/stories/838, type=cover), Item(name=1 of 1 -  Secret of the Spider Shop, resourceURI=http://gateway.marvel.com/v1/public/stories/842, type=cover), Item(name=Cover #866, resourceURI=http://gateway.marvel.com/v1/public/stories/866, type=cover), Item(name=Fantastic Four (1998) #512, resourceURI=http://gateway.marvel.com/v1/public/stories/912, type=cover), Item(name=Interior #913, resourceURI=http://gateway.marvel.com/v1/public/stories/913, type=interiorStory), Item(name=Fantastic Four (1998) #513, resourceURI=http://gateway.marvel.com/v1/public/stories/916, type=cover), Item(name=Interior #917, resourceURI=http://gateway.marvel.com/v1/public/stories/917, type=interiorStory), Item(name=Cover #977, resourceURI=http://gateway.marvel.com/v1/public/stories/977, type=cover), Item(name=Cover #979, resourceURI=http://gateway.marvel.com/v1/public/stories/979, type=cover), Item(name=Amazing Spider-Man (1999) #500, resourceURI=http://gateway.marvel.com/v1/public/stories/1018, type=cover), Item(name=Interior #1019, resourceURI=http://gateway.marvel.com/v1/public/stories/1019, type=interiorStory)], returned=20), thumbnail=Thumbnail(extension=jpg, path=http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b), urls=[MarvelUrl(type=detail, url=http://marvel.com/comics/characters/1009610/spider-man?utm_campaign=apiRef&utm_source=ee07ff234684195576531c4c31b8a62c), MarvelUrl(type=wiki, url=http://marvel.com/universe/Spider-Man_(Peter_Parker)?utm_campaign=apiRef&utm_source=ee07ff234684195576531c4c31b8a62c), MarvelUrl(type=comiclink, url=http://marvel.com/comics/characters/1009610/spider-man?utm_campaign=apiRef&utm_source=ee07ff234684195576531c4c31b8a62c)])], total=1), etag=0494cc9649cf571651f5cebdb7188987dae242de, status=Ok)> but was:<null>

}