Ligeti Károly összeszedte a 2021.06.09-én tartott konzultáció tanulságai alapján, hogy hogyan
kell/érdemes nekikezdeni a GitHub használatával történő közös munkának:

Közös munka a GitHub-on. Új repo létrehozás
- Repositories -> New
2. Clone repo sajátgépre
- Giten kívüli mappa létrehozás: `mkdir {mappanév}`
- Mappába belépni: `cd {mappanév}`
- Klónozás: `git clone https://github.com/{felhasználónév}/{reponév}`
3. IDEA-ban új projekt
- Verziószámra figyelni (mindenkinek azonos JDK verzió kell!)
4. A korábban klónozott mappában létrehozni a projektet
5. .gitignore létrehozás
- IDEA esetén minimálisan ezeket kell figyelmen kívül hagyni: .idea, target, *.iml
6. pom.xml-t hozzáadni a githez
- pom.xml kiválaszt, majd CTRL + ALT + A.
7. Ezzel megvan az alap projekt, mehet fel a gitre
- `Init commit` message
- [Commit and Push...] A git nem tárol üres könyvtárakat! Csak azok látszanak, amelyeknek tartalma is van. Így a
  mavenes fájlszerkezet ilyenkor még nem látszik, csak a létrehozó gépén. Érdemes lehet a következőket megtenni
  a közös munka megkezdése előtt:
- src -> main -> java-ban egy alap osztály létrehozása
  pl. ha a projekt témája Movie, akkor egy `movie` package-ben `Movie` osztály.
- src -> test -> java-ban az alap osztályhoz tesztosztály legenerálása
  ALT + ENTER a `main -> java` movie.Movie osztálynéven a szerkesztőablakban, `Create Test` -> [OK].
  Ha ezekre az osztályokra mégsincs szükség, törölhetőek. Ha előre tudjuk, milyen osztálynevekkel
  kell dolgozni, természetesen érdemes ezeket létrehozni. Elegendő egyet a mainben, egyet a tesztben.
  És mehet a Commit, majd a Push.Ekkor érdemes hozzáadni fejlesztőtársakat (Collaborator) a projekthez:
- Settings -> Manage access
- [Invite a collaborator]
- Megadható felhasználónév, teljes név vagy email
- [Add {meghívott} to this repository]
- Ekkor a meghívott kap egy email-t, melyben a [View invitation]-re kattintva kap hozzáférést a repohoz. FONTOS!!!
  `push` előtt mindig `pull`!
- `fetch` (ezzel meg lehet nézni, mi változott, de nem írja felül a saját fájlokat)
- `pull` (egyből behúzza a változásokat)
  `pull` előtt commitolj, különben a változásokat sem engedi lehúzni a gited!
  
Kiegészítés:

- Ha a fejlesztőtárs letölti a GitHub repoból az anyagot és furcsán jelennek meg az osztályok a projekt 
  struktúrában, akkor Maven/Reload All Maven Projects segíthet
- Annak a dolga, aki becsatlakozik a fejlesztésbe: 
  Sajátgépen létrehozni egy giten kívüli mappát.
  Elfogadni a meghívót, majd a mappába belépve parancssorból git clone-nal lehúzni a repo tartalmát.