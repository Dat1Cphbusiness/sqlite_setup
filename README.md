# SQLite Demo Kode

Dette lille projekt viser hvorman man kan organisere sin kode, 
så det er forholdsvist enkelt at arbejde med en SQLite database.

## Opsætning af projekt

Klon dette repository. Hent de to Maven dependencies, som er angives i `pom.xml`:

```xml
<dependencies>
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.46.0.1</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.36</version>
        </dependency>
    </dependencies>
```

Du skal tilknytte filen **data/dogs.db** til din 
database-konfiguration til højre i IntelliJ's database plugin.
Nu skulle du gerne kunne se tabellen **dogs** og der er 50 rækker i tabellen.

## Kodeoverblik

- `src/main/java/app/Main.java`: Programmets entrypoint. Angiver JDBC-URL'en `jdbc:sqlite:data/dogs.db`, opretter `DBConnector` og `DogDAO` og printer alle hunde via `findAll()`.
- `src/main/java/app/entities/Dog.java`: POJO der repræsenterer en række i `dogs`-tabellen med felterne id, name, age, breed, nickName og weight samt en `toString()` til logudskrift.
- `src/main/java/app/persistence/DBConnector.java`: Lille wrapper om `DriverManager.getConnection(...)` så resten af koden ikke kender JDBC-URL'en direkte.
- `src/main/java/app/persistence/DogDAO.java`: CRUD-metoder til `dogs`-tabellen (`createTable`, `insertDog`, `findAll`, `findById`, `updateDog`, `deleteDog`). Hver metode åbner en ny forbindelse med try-with-resources og bruger prepared statements.

## Projektstruktur

```
.
├─ pom.xml
├─ README.md
├─ data/
│  ├─ dogs.db
│  └─ dogs.sql
└─ src/
   └─ main/
      └─ java/
         └─ app/
            ├─ Main.java
            ├─ entities/
            │  └─ Dog.java
            └─ persistence/
               ├─ DBConnector.java
               └─ DogDAO.java
```

### Datafiler

- `data/dogs.db`: SQLite-database med tabellen `dogs` og 50 eksempelrækker.
- `data/dogs.sql`: SQL-script der opretter tabellen og indsætter de samme testdata.

## Sådan kører du

1. Sørg for at `data/dogs.db` ligger i projektroden (den er allerede committet).
2. Kør `mvn -q compile` for at hente dependencies.
3. Start programmet via dit IDE (kør `Main`)

Programmet skriver alle hunde til konsollen, én linje per `Dog`-instans.
