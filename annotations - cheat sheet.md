# Annotációk – Cheat sheet

## JUnit

* `@Test` - Tesztmetódusra, ezáltal látja a JUnit, hogy ez egy tesztmetódus.
* `@BeforeEach` - Az ilyen annotációval ellátott metódust a JUnit minden egyes tesztmetódus előtt lefuttatja.
* `@AfterEach` - Az ilyen annotációval ellátott metódust a JUnit minden egyes tesztmetódus után lefuttatja.
* `@BeforeAll` - Az ilyen annotációval ellátott metódus csak egyszer fut le, az adott osztályban szereplő 
  összes teszteset lefutása előtt.
* `@AfterAll` - Az ilyen annotációval ellátott metódus csak egyszer fut le, az adott osztályban szereplő 
  összes teszteset lefutása után.
* `@DisplayName()` - Tesztesetre tehetjük rá, paraméterként pedig megadhatjuk neki azt a nevet, amelyet 
  szeretnénk, hogy a lefuttatáskor megjelenjen a fejlesztőeszközben vagy a reportban.
* `@DisplayNameGeneration()` - Tesztesetre tehetjük rá, paraméterként pedig megadhatjuk neki, hogy az adott 
  metódus- vagy osztálynévből milyen módon generálja a lefuttatáskor a fejlesztőeszközben vagy a reportban 
  megjelenő nevet. Vannak rá beépített implementációk, de magunk is írhatunk egyet.
* `@Disabled` - Ha nem szeretnénk, hogy egy tesztosztály vagy egy konkrét teszteset lefusson.
* `@DisabledXXX` - A teszteset kikapcsolása valamilyen feltétel alapján (Pl. `@DisabledOnOs(OS.WINDOWS)`)
* `@TestMethodOrder()` - Akkor használjuk, ha valamiért fontos megadnunk a tesztesetek lefutási sorrendjét. 
  `MethodOrderer` implementációval lehet paraméterezni.

    * `MethodOrderer.Alphanumeric`: ábécé-sorrendben futtatja le a teszteseteket;
    * `MethodOrderer.OrderAnnotation`: egy egész számokkal paraméterezhető `@Order` annotációt kell a 
      tesztesetekre rakni, a lefuttatás a számok sorrendjében történik;
    * `MethodOrderer.Random`: véletlenszerű sorrend, ez az alapértelmezett).

* `@TestFactory` - Ha `DynamicTest` típusú, dinamikus teszteset példányokat szeretnék kapni.
* `@RepeatedTest()` - Ha azt szeretnénk, hogy egy teszteset többször fusson le (különböző bemeneti paraméterekkel). 
  Első paraméterül egy egész számmal kell megadni, hogy hányszor szeretnénk a lefutást.
* `@ParameterizedTest` - Paraméterezett tesztek írására. Egy `name` paraméterként megadhatjuk neki a teszteset nevét 
  is, hogy hogyan kerüljön megjelenítésre a felhasználói felületen vagy a reportban. Ezenkívül még egy további 
  annotációkat kell egy ilyen tesztesetre rátenni, amelyben  megadjuk a paramétereket, hogy milyen értékekkel 
  futtassa le a JUnit ezt a tesztesetet:

    * `@ValueSource()`: Ennek az annotációnak egyszerű értékeket lehet paraméterül megadni 
      (`strings`, `ints`, `doubles`, stb.) Nem csak a beviteli értékeket lehet megadni, hanem az elvárt 
      értékeket is, második paraméterként.
    * `@NullSource`: Ha szeretnénk, hogy a JUnit meghívja az adott tesztmetódust `null` értékkel is
    * `@EmptySource`: Ha String vagy kollekció a paraméter típusa, ekkor üres stringgel vagy kollekcióval fogja 
      meghívni a metódust.
    * `@NullAndEmptySource`: Mindkét módon, tehát `null` értékű és üres stringgel vagy kollekcióval is meghívja 
      az adott metódust.
    * `@EnumSource()`: Ezen annotáció használatával megadhatjuk egy enum különböző értékeit is paraméterként.
    * `@MethodSource()`: Paraméterül egy statikus metódus nevét kell neki megadni. A paraméterezett teszt 
      meghívásához a paramétereket ez a metódus fogja előállítani. Ez a metódus egy `Stream`, egy `Collection`, 
      egy `Iterator` vagy egy `Iterable` példányt adhat vissza.
    * `@ArgumentsSource`: Ezzel az annotációval egy `ArgumentsProvider` implementációt adhatunk át paraméterül. 
      Ennek az interfésznek egyetlen metódusa van, amellyel egy `Stream` formájában adhatjuk meg a paramétereket.
    * `@CsvSource`: Ezzel az annotációval van lehetőség az állomány tartalmát `String[]` formájában közvetlenül 
      a forráskódban megadni.
    * `@CsvFileSource`: Ennek az annotációnak pedig egy, a classpath-on lévő CSV-fájlt adhatunk meg. Ezenkívül 
      több más paraméter is megadható, például a karakterkódolás, vagy hogy milyen típusú sortörés karakterrel 
      szeretnénk dolgozni.

* `@Nested` - Hierarchiát építhetünk föl a tesztesetek között belső osztályok használatával. Ez akkor hasznos, 
  ha valamiféleképpen csoportosítani szeretnénk a teszteseteinket, és a különböző csoportok inicializációjakor 
  van egy közös rész is, illetve csoportonként pedig egy különböző rész is. A belső osztályokat kell ezzel az 
  annotációval ellátni.
* `@Tag` - A JUnit unit tesztjeit el lehet látni _tag_-ekkel is, ezzel csoportosítva a teszteseteket. Ezt az 
  annotációt rátehetjük az egész tesztosztályra vagy egyes tesztesetekre is, egy helyen egyszerre akár többet is.
* Metaannotációk létrehozásakor meg kell adni:

    * az új annotáció nevét (`public @interface ServiceTest`, ezzel a `@ServiceTest` annotáció jön létre.)
    * mire tehető rá a létrehozott új annotáció (`@Target(ElementType.TYPE,  ElementType.METHOD)`, vagyis 
      osztályra és metódusra),
    * mikor kerüljön feldolgozásra ez az új annotáció (`@Retention(RetentionPolicy.RUNTIME)`, vagyis futásidőben),
    * azt, hogy tesztesetnél akarjuk használni ezt az annotációt (`@Test`)
    * kerülhetnek rá további más annotációk is, amelyeket az általunk létrehozott metaannotáció ezáltal „hordozni” fog,
    * és kerülhet rá tag is (pl. `@Tag("service")`).

* `@TempDir` - Fájlműveletek teszteléséhez kapunk ezáltal egy ideiglenes könyvtárat. Ez úgy működik, hogy a JUnit 
  a teszteset lefutása előtt létrehoz egy könyvtárat az adott operációs rendszer `temp` könytárán belül, elvégzi 
  benne a fájlműveletek tesztelését, a teszteset végén pedig letörli ezt a könyvtárat.

## AssertJ

* `@ExtendWith(SoftAssertionsExtension.class)`: A tesztosztályra lehet rátenni ezt az annotációt. Soft assertek 
  írásához kap az osztály parameter injectionnel egy `SoftAssertions` példányt.

## Mockito

* `@ExtendWith(MockitoExtension.class)` - A tesztosztályra kell rátenni, és ekkor nem mi hozzuk létre a mock 
  objektumot, hanem a Mockito.
* `@Mock` - A mockolni kívánt osztályt felvesszük attribútumként, és ezzel az annotációval látjuk el. Ebből 
  tudja a Mockito, hogy ezt az osztályt kell majd mockolni.
* `@InjectMocks` - Ezzel az annotációval pedig azt az osztályt vesszük fel attribútumként, amelynek a mockolt 
  osztályt dependency injectionnel át akarjuk adni.

## Spring és Spring Boot

* `@ComponentScan` - Osztályra tehető rá. A Spring az adott csomagban és az alatta lévő csomagokban található 
  összes Spring Beant megtalálja és felolvassa, vagyis azokat az osztályokat, amelyeken rajta van a következő 
  annotációk valamelyike: `@Component`, `@Repository`, `@Service`, `@Controller` vagy `@RestController`.
* `@Component` - Ez jelzi az általános beaneket.
* `@Repository` - Ez jelzi a perzisztens rétegbe tartozó beaneket.
* `@Service` - Ez jelzi az üzleti logika rétegbe tartozó beaneket.
* `@Controller` - Ez pedig a prezentációs rétegbe tartozó
  beaneket.
* `@RestController` - A `@Controller` annotációt egészíti ki további funkcionalitással (REST webszolgáltatások 
  használata esetén, ld. később).
* `@Configuration` - Osztályra tehető rá, ebben az osztályban tudunk egyedi
  konfigurációt Java kóddal megadni. Egy ilyen osztályban lehet megadni Spring Beaneket is a `@Bean` annotációval.
* `@Bean` - Ha rátesszük egy metódusra, akkor a metódus által visszaadott objektumot a Spring Spring Beanként
  fogja kezelni, azaz elhelyezi az Application Contextben.
* `@Autowired` - Attribútumra, konstruktorra vagy setter metódusra tehető rá egy komponensben. A Spring ennek az
  attribútumnak fog értéket adni, vagy ezt a konstruktort vagy metódust fogja meghívni, azaz elvégezi az injectiont.
* `@SpringBootApplication` - Azt az osztályt jelöljük vele, amelyben az alkalmazás belépési pontja, a `main()` 
  metódus található. Metaannotáció, vagyis több más annotáció is található rajta:

    * `@EnableAutoConfiguration`: Ha a classpath-on talál valamilyen library-t, amihez van konfigurációja, akkor 
      azt automatikusan konfigurálja föl, automatikusan tegye elérhetővé a fejlesztő számára.
    * `@SpringBootConfiguration`: Ezen az annotáción rajta van a `@Configuration` annotáció, ami azt jelenti, 
      hogy ebben az osztályban is tudunk konfigurációt Java kóddal megadni.
    * `@ComponentScan`: Ld. korábban.

## Lombok

* `@Data` - Osztályra tehető. Kódot generál, egyszerre helyettesíti a következő annotációkat:

    * `@ToString`
    * `@EqualsAndHashCode`
    * `@Getter` (minden attribútumon)
    * `@Setter` (minden nem _final_ attribútumon)
    * `@RequiredArgsConstructor`

* `@NoArgsConstructor` - Osztályra tehető. Legenerálásra kerül egy paraméter
  nélküli konstruktor.
* `@AllArgsConstructor` - Osztályra tehető. Legenerálásra kerül egy olyan konstruktor, amely minden attribútumnak paraméter által 
  kezdőértéket ad.
* `@Slf4j` - Osztályra tehető. Legenerálásra és példányosításra kerül egy `Logger` típusú attribútum.

## Spring MVC

* `@RequestMapping` - Ezzel tudjuk megmondani, hogy milyen URL-en figyeljen az adott metódus. Ezt meg lehet adni 
  osztályszinten is, ekkor az összes metódusra vonatkozik. Ha mindkét helyen szerepel (osztály- és metódusszinten), 
  akkor konkatenálódik, tehát összeadódik.
* `@GetMapping` - `GET` HTTP kérésre válaszoló metódusra teendő rá, a `@RequestMapping` annotáció továbbfejlesztése.
* `@PostMapping` - `POST` HTTP kérésre válaszoló metódusra teendő rá, a `@RequestMapping` annotáció továbbfejlesztése.
* `@PutMapping` - `PUT` HTTP kérésre válaszoló metódusra teendő rá, a `@RequestMapping` annotáció továbbfejlesztése.
* `@DeleteMapping` - `DELETE` HTTP kérésre válaszoló metódusra teendő rá, a `@RequestMapping` annotáció továbbfejlesztése.
* `@ResponseBody` - Ha egy metódusból visszatérünk, azt egy template-névként próbálja a Spring értelmezni. Akkor kell a 
  metódusra rátenni ezt az annotációt, ha meg akarjuk mondani a Springnek, hogy a visszatérési értéket alakítsa át JSON 
  formátumúvá. Azért, hogy ezt ne kelljen ténylegesen minden metóduson megtenni, létrehozták a `@RestController` 
  annotációt, amely a `@Controller` annotációt annyival egészíti ki, hogy az osztályban lévő összes metódust implicit 
  ellátja ezzel az annotációval.
* `@RequestParam` - Ahhoz, hogy az URL-nek paramétereket adhassunk át, a controller osztály metódusában fel kell 
  vennünk egy paramétert és ellátni ezzel az annotációval. Arról is dönthetünk, hogy ez egy kötelező paraméter 
  legyen vagy csak opcionális.
* `@PathVariable` - Ha URL részleteket akarunk megadni a `@GetMapping` annotáció paramétereként. Itt, amikor 
  definiáljuk, hogy milyen URL-en legyen elérhető az adott metódus, akkor egy kapcsos zárójelekkel megadott 
  placeholdert kell használnunk. Természetesen itt is működik a típuskonverzió.
* `@RequestBody` - Ahhoz, hogy a HTTP kérés törzsében adatot lehessen beküldeni. Az adatot lehet pl. JSON formátumban
  beküldeni, és a HTTP válaszban visszakapott adat is ilyen módon érkezik meg.
* `@ResponseStatus(HttpStatus.XXX)` - Megadhatjuk, milyen HTTP státuszkóddal térjen vissza a metódus.
* `@ExceptionHandler` - Controller osztályon belüli metódusra tehető rá, lokális kivételkezelést jelölünk vele.
* `@ControllerAdvice` - Globális hibakezelő osztályra tehető rá, az ilyen osztályban `@ExceptionHandler` annotációval 
  ellátott hibakezelő metódusok találhatóak.

## Integrációs tesztelés

* `@SpringBootTest` - Spring Boot integrációs tesztosztályokat kell ezzel jelölni. Ezáltal a teljes alkalmazás el 
  fog indulni. Ez egy metaannotáció, szerepel rajta egy `@ExtendWith(SpringExtension.class)` annotáció. Ez azt 
  mondja meg a JUnit 5-nek, hogy ezt a tesztosztályt az `SpringExtension`-nel kell lefuttatni. Paraméterül meg 
  lehet adni azt is, hogy a Tomcat konténer egy random porton kerüljön elindításra (amelyik éppen szabad). 
  (`@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`)
* `@LocalServerPort` - Amennyiben tudni szeretnénk, hányas porton fut a konténer, akkor a port számát ezzel az 
  annotációval injektálhatjuk a tesztesetbe.
* `@AutoConfigureMockMvc` - Abban az esetben, ha a teljes alkalmazást szeretnénk tesztelni, de konténer nélkül, 
  a `@SpringBootTest` annotációt használjuk, az `@AutoConfigureMockMvc` annotációt pedig azért, hogy a MockMvc 
  keretrendszer automatikusan fel legyen konfigurálva. Ebben az esetben nem kell mockolni a service osztályunkat, 
  mert a valódi service-t használjuk, az kerül lepéldányosításra.
* `@WebMvcTest` - Spring MVC használatakor a controller réteg teszteléséhez kell a tesztosztályra rátenni.
* `@MockBean` - A mock objektumokat kell ezzel az annotációval ellátni. A Mockito keretrendszerrel lehet létrehozni 
  és paraméterezni őket.

## Swagger UI

* `@Tag` - Ezzel az annotációval lehet megadni egyedi elnevezést a controller osztálynak, hogy a dokumentációban 
   mi jelenjen meg.
* `@Schema()` - Személyre szabható, hogy az egyes attribútumok mellett mi jelenjen meg névként a felhasználói felületen, 
   sőt, egy értéket is meg lehet adni példaként.
* `@Operation` - Magáról az adott metódusról ezzel az annotációval tudunk megadni rövidebb és hosszabb leírást, amely 
   bele fog kerülni a dokumentációba.
* `@ApiResponse` - Ezzel az annotációval felvehetjük, hogy a metódus milyen további státuszkódokkal képes még visszatérni, 
   ezek mit jelentenek, és a dokumentációban természetesen ez is meg fog jelenni.

## JAXB

* `@XmlRootElement()` - Olyan osztályra teendő rá, amelyet szeretnénk XML formátumban kiírni, vagy abból beolvasni.
* `@XmlElement` - Konfigurálható vele az XML tag neve.
* `@XmlAccessorType()` - ha a JAXB annotációkat nem a getterre, hanem az attribútumra akarjuk rátenni, akkor használjuk.

## Spring Boot validáció beépített annotációk

* `@AssertTrue` és `@AssertFalse` - Egy `boolean` típusú adat esetén lehet vizsgálni, hogy `true` vagy `false`-e az értéke.
* `@Null` és `@NotNull` - Referencia típusú adat esetén lehet megmondani, hogy az adott mező értéke lehet-e `null` vagy nem.
* `@Size` - String vagy kollekció esetén meg lehet határozni méretkorlátokat.
* `@Max`, `@Min`, `@Positive`, `@PositiveOrZero`, `@Negative`, `@NegativeOrZero`, `@DecimalMax`,  `@DecimalMin`, `@Digits` - Egész- 
és lebegőpontos számoknál meg lehet határozni minimum és maximum értékeket, elvárást az előjelre vonatkozóan, illetve azt is, 
hogy hány számjegyből álljon.
* `@Future`, `@Past`, `@PastOrPresent`, `@FutureOrPresent` - Különböző dátum és idő típusú attribútumoknál meg lehet határozni, 
hogy az a múltban vagy a jövőben legyen.
* `@Pattern` - Meg lehet határozni, hogy egy string feleljen meg egy reguláris kifejezésnek.
* `@Email` - Meg lehet nézni egy stringről, hogy e-mail cím formátumú-e.
* `@NotEmpty` - Meg lehet határozni, hogy egy string vagy kollekció nem lehet üres.
* `@NotBlank` - Meg lehet határozni, hogy egy string nem lehet üres, `null`, illetve nem tartalmazhat csupa whitespace karaktereket.

* `@Valid` - Ahhoz, hogy a Spring automatikusan megvizsgálja a paramétereket (a Dto-kat) az adott controller metódus meghívásakor, 
az átadott paraméter elé el kell helyezni ezt az annotációt.
* `@Validated` - Amennyiben egy osztályon szerepel ez az annotáció, akkor a Spring erre az osztályra rá fogja futtatni a Bean Validationt.
* `@Constraint` - Saját validációs annotáció létrehozásakor. Ahhoz, hogy az általunk létrehozott új annotáció tényleg egy validációs 
annotáció legyen, rá kell tenni ezt az annotációt, amelynek paraméterül meg kell adni a validálást elvégző osztály nevét.

## Spring Boot konfiguráció beolvasása:

* `@Value` - Konfigurációs értékek injektálására is használható. Paraméterként meg kell adni a kívánt konfigurációs érték kulcsát.
* `@ConfigurationProperties` - Ezt az annotációt egy olyan osztályra tesszük, melynek attribútumait konfigurációból töltjük fel.
* `@EnableConfigurationProperties()` - Az előző annotációval ellátott konfigurációs osztályra kell ezzel az annotációval hivatkozni,
ekkor példányosítja a Spring, az `application.properties`-ben szereplő értékekkel feltöltve.
