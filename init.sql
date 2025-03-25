-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: filmdb
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `film_entity`
--

DROP TABLE IF EXISTS `film_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_entity` (
  `id` int NOT NULL,
  `available` int DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_entity`
--

LOCK TABLES `film_entity` WRITE;
/*!40000 ALTER TABLE `film_entity` DISABLE KEYS */;
INSERT INTO `film_entity` VALUES (1,1,'https://filmscover.s3.us-east-2.amazonaws.com/18993ad7-d5af-4c60-bf1e-c444d0aaeb9e.jpg','La Nueva República (NR), también conocida como simplemente como la República, la Alianza o la Rebelión, fue el gobierno democrático galáctico formado a partir de las cenizas del conflicto durante los últimos años de la Guerra Civil Galáctica, absorbiendo el liderazgo político y militar de la antigua Alianza para Restaurar la República después de la Batalla de Endor en el 4 DBY. La Nueva República siguió los pasos de la República Galáctica, comúnmente conocida en ese momento como la \'Antigua República\' durante la Era del Imperio, restaurando el Senado Galáctico y luchando contra las destrozadas fuerzas del Imperio Galáctico. A diferencia de su predecesora, la capital de la Nueva República no estaba en Coruscant, y después de un breve periodo en Chandrila, estableció su capital en una variedad de mundos miembros en forma rotativa, elegida mediante elección democrática.',25000,'2019-12-19',20,'Star Wars, El ascenso de Skywalker'),(2,1,'https://filmscover.s3.us-east-2.amazonaws.com/90606d94-6d9e-4e29-aa5a-10dc3f313022.jpg','Avengers: Endgame, es una película de superhéroes estadounidense de 2019 basada en el equipo de superhéroes de Marvel Comics, Los Vengadores. Producida por Marvel Studios y distribuida por Walt Disney Studios Motion Pictures, es la secuela directa de Avengers: Infinity War (2018) y la película número 22 del Universo cinematográfico de Marvel (UCM), y hasta la fecha la más larga. Dirigida por Anthony y Joe Russo y escrita por Christopher Markus y Stephen McFeely, la película cuenta con un reparto coral que incluye a Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner, Don Cheadle, Paul Rudd, Brie Larson, Karen Gillan, Danai Gurira, Benedict Wong, Jon Favreau, Bradley Cooper, Gwyneth Paltrow y Josh Brolin. En la película, los miembros supervivientes de los Vengadores y sus aliados intentan revertir el daño causado por Thanos en Infinity War.',22000,'2019-04-12',10,'Avengers: Endgame'),(3,1,'https://filmscover.s3.us-east-2.amazonaws.com/cea19855-5476-427d-af32-bb09e9321326.jpg','El Señor de los Anillos: el retorno del Rey (título original en inglés: The Lord of the Rings: The Return of the King) es la tercera película de la trilogía cinematográfica de El Señor de los Anillos, dirigida por Peter Jackson y basada en la tercera parte de la obra de J. R. R. Tolkien El Señor de los Anillos. Tuvo un presupuesto de 94 millones de dólares y fue rodada del 11 de octubre de 1999 al 22 de diciembre de 2000. Trata sobre la última parte del viaje que emprenden los nueve compañeros (de los cuales quedan solamente ocho) para salvar a la Tierra Media de la oscuridad impuesta por Sauron. En esta parte se decide el destino de todos los habitantes de estas tierras. Los primeros dos filmes fueron El Señor de los Anillos: la Comunidad del Anillo y El Señor de los Anillos: las dos torres, aunque en esta película se incluyen algunos eventos del libro anterior: Las dos torres. En una edición especial extendida se introdujeron nuevas escenas que ayudan a comprender mejor el contexto de la película y a cada uno de los personajes. Estas escenas se suprimieron en la versión original porque alargaban demasiado la película y le restaban dinamismo.',14000,'2003-12-17',5,'El Señor de los Anillos: el retorno del Rey'),(4,1,'https://filmscover.s3.us-east-2.amazonaws.com/62b579c8-1214-43d7-88ec-bb3b22917fab.jpg','Avatar (comercializada como Avatar de James Cameron) es una película épica de ciencia ficción militar y animación estadounidense de 2009. Está ambientada en el año 2154 y los acontecimientos que se narran en la historia Pandora, una luna (y aparentemente la más grande) de un planeta similar a Júpiter llamado Polifemo, habitada por una especie humanoide llamada na’vi, con la que los humanos se encuentran en conflicto debido a que uno de sus clanes está asentado alrededor de un gigantesco árbol que cubre una inmensa veta de un mineral muy cotizado y que supondría la solución a los problemas energéticos de la Tierra: el unobtainium.7​8​Jake Sully, un marine que quedó paralítico, es seleccionado para participar en el programa Avatar, un proyecto que transporta la mente de los científicos a unos cuerpos artificiales de na’vi para que la comunicación con los nativos resulte así más sencilla.9​Durante esa búsqueda de confianza entre Jake y los na’vi, Jake debe ser aprobado por la tribu y experimenta todas las relaciones con el bosque, la fauna y la flora que tienen los nativos, junto con sus costumbres y su lengua. A pesar del fin científico del proyecto, el coronel Quaritch, quien dirige la defensa de la base humana en Pandora, convence a Jake para que le proporcione información sobre los nativos en caso de que fuera necesario recurrir a la fuerza para que se marchen. En un principio, Jake cumple profesionalmente su misión, pero se enamora de una de las nativas, Neytiri, y se da cuenta de que estos jamás renunciarán a su tierra, haciendo inevitable un conflicto armado; él deberá decidir de qué lado está.',11000,'2009-12-18',8,'Avatar'),(5,1,'https://filmscover.s3.us-east-2.amazonaws.com/3d1f100c-ece1-4522-be47-e81ce46905ce.jpg','The Dark Knight (conocida en Hispanoamérica como Batman: El caballero de la noche y en España como El caballero oscuro) es una película de superhéroes de 2008 dirigida por Christopher Nolan a partir de un guion coescrito con su hermano Jonathan. Basada en el superhéroe Batman de DC Comics, es la secuela de Batman Begins (2005) y la segunda entrega de la trilogía The Dark Knight. La trama sigue al justiciero Batman, el teniente de policía James Gordon y el fiscal de distrito Harvey Dent, quienes forman una alianza para desmantelar el crimen organizado en Gotham City. Sus esfuerzos se ven descarrilados por el Joker, un cerebro anarquista que busca probar hasta dónde llegará Batman para salvar a la ciudad del caos. El reparto incluye a Christian Bale, Michael Caine, Heath Ledger, Gary Oldman, Aaron Eckhart, Maggie Gyllenhaal y Morgan Freeman.',15000,'2008-07-18',6,'The Dark Knight'),(6,1,'https://filmscover.s3.us-east-2.amazonaws.com/99d77e9d-1ee3-4511-9c70-6ed81810af6c.jpg','Happy (Adam Sandler) es un aspirante jugador de hockey sobre hielo que domina un poderoso tiro que su padre le enseñó cuando era niño. Sin embargo, su agresión y la pobre habilidad para patinar sobre hielo, le hace imposible ingresar en cualquier equipo. Su abuela (Frances Bay), con quien ha vivido casi toda su vida debido a un infortunado incidente en hockey que mató a su padre, no ha pagado impuestos de su casa en muchos años. Ella debe 270.000 dólares al IRS y la casa que el abuelo de Happy “construyó con sus propias manos” está a punto de ser embargada. Debido a una serie de eventos que llevan a Happy a creer en su habilidad para utilizar su poderoso tiro en el golf, se une al torneo profesional para poder ganar dinero suficiente y así recuperar la casa de su abuela del gobierno. Su rival Shooter McGavin (Christopher McDonald) ve a Gilmore como una amenaza, e intenta impedir cualquier intento de este para quitarle el trono. Aunque su juego y sus modales en el green no son refinados, Happy es guiado por un entrenador de golf manco (Carl Weathers) y la Directora de Relaciones Públicas del torneo (Julie Bowen), quien lo ayuda a encontrar su propio camino para ganar torneos “con la cabeza fría”. Después de firmar un contrato para ser patrocinado por Subway, Happy consigue los 270.000 dólares que necesita para recuperar la casa de su abuela. En la subasta, es vencido por Shooter McGavin, quien le ofrece a Happy la casa a cambio de que abandone el torneo. Al principio Happy iba a aceptar el trato de Shooter, pero es convencido de continuar al darse cuenta de que su abuela preferiría ver a su nieto tener éxito que tener la casa. Shooter acepta darle a Happy la casa si lo vence en el Tour Championship. Happy gana el torneo, se convierte en un golfista profesional y finalmente recupera la casa de su abuela.',8000,'1996-02-16',5,'Happy Gilmore'),(7,1,'https://filmscover.s3.us-east-2.amazonaws.com/00d2eccc-70c6-4018-9d7e-f32e5d91f20f.jpg','Charlie Baileygates (Jim Carrey) es agente de la policía de Rhode Island, siempre atento y servicial con su prójimo. Sin embargo, su amabilidad en extremo lo pone en situaciones vergonzosas y hasta humillantes. Todo esto empeoró cuando en su luna de miel, su esposa Layla (Traylor Howard) tiene un cruce de miradas con el chofer de limusina (Tony Cox) lo que derivó en el divorcio de ella y Charlie para irse con él; y deja a sus tres hijos (que eran de Layla y el chofer) a su cargo.',10000,'2000-06-23',9,'Irene, yo y mi otro yo'),(8,1,'https://filmscover.s3.us-east-2.amazonaws.com/444280f8-6e61-4023-a00d-57227d52c828.jpg','Yuri Orlov es un traficante de armas astuto, soberbio y sin pelos en la lengua. Aunque su trabajo es muy sacrificado y tenga que recorrer las zonas de guerras más peligrosas está muy orgullo y presume de él. Nunca ha tenido ningún dilema moral a la hora de traficar con armamento, lo único que le preocupa es la interpol, su competencia y algún cliente. Pero tarde o temprano abrirá los ojos y se encontrará cara a cara con su conciencia.',15000,'2005-09-16',8,'El señor de la guerra'),(9,1,'https://filmscover.s3.us-east-2.amazonaws.com/7fb3173a-8124-4e22-9687-e241172412cd.jpg','En el planeta Tierra antes del comienzo de la vida. Al borde de una cascada, un extraterrestre humanoide espera mientras una enorme nave espacial, que permanecía inmóvil, empieza a alejarse. El humanoide bebe un líquido negro burbujeante y empieza a desintegrarse. Cuando sus restos corporales caen por la cascada, el ADN del extraterrestre sufre una reacción biogenética dando así el paso para crear la vida en la Tierra.6​. En 2089, los arqueólogos Elizabeth Shaw y Charlie Holloway descubren un mismo mapa estelar en escrituras de varias antiguas culturas sin contacto entre ellas. Eso se interpreta como una invitación de los precursores o diseñadores de la humanidad, sus «Ingenieros», a visitar un sistema planetario que aparece en el mapa. Peter Weyland, el anciano fundador y dueño de la Corporación Weyland, reúne fondos para la creación y lanzamiento de la nave científica Prometheus y viajar hasta la luna distante LV-223, la única habitable en el sistema planetario del mapa.',18000,'2012-06-15',11,'Prometheus'),(10,1,'https://filmscover.s3.us-east-2.amazonaws.com/8a220fd2-06e7-40b3-97c8-b26e86b0e293.jpg','Ambientada en 2004, la película sigue a un grupo de paleontólogos, arqueólogos y otros, reunidos por el multimillonario Charles Bishop Weyland (Lance Henriksen) para una expedición cerca de la Antártida para investigar una señal de calor misterioso. Weyland espera para reclamar como suyo el hallazgo, y su grupo descubre una pirámide debajo de la superficie de una abandonada estación de caza de ballenas. Los jeroglíficos y esculturas revelan que la pirámide es un coto de caza para los Depredadores que matan a los Aliens como un rito de pasaje. Los seres humanos están atrapados en medio de una batalla entre las dos especies y tratan de evitar que los Aliens lleguen a la superficie.',18000,'2012-06-15',11,'Alien vs. Predator'),(11,1,'https://filmscover.s3.us-east-2.amazonaws.com/569c979a-57c4-4c62-9a77-77b9ce33306b.jpg','Es la octava producción dirigida por Hayao Miyazaki dentro de Studio Ghibli y la decimoquinta del estudio. La película está ambientada en un reino ficticio en el cual tanto la magia como la tecnología del siglo XX están presentes. Sophie es una joven sombrerera que es víctima del hechizo de una bruja y que decide ir al castillo ambulante, lugar habitado por el mago Howl, para ver si puede encontrar una solución al maleficio.',8000,'2004-11-20',10,'El castillo ambulante'),(12,1,'https://filmscover.s3.us-east-2.amazonaws.com/e536c989-c333-4b1e-a5f5-477ede2f8362.jpg','El viaje de Chihiro (千と千尋の神隠し Sen to Chihiro no Kamikakushi?, lit. La misteriosa desaparición de Sen y Chihiro)3​ es una película de animación japonesa estrenada el 20 de julio de 2001. Fue dirigida por Hayao Miyazaki y producida en el Studio Ghibli, por Tokuma Shoten, Nippon Television Network, Dentsu, Buena Vista Home Entertainment, Tohokushinsha Film, Mitsubishi y distribuida por Tōhō. El elenco principal está compuesto por Rumi Hiiragi, Miyu Irino, Mari Natsuki, Takeshi Naitō, Yasuko Sawaguchi, Tsunehiko Kamijō, Takehiko Ono y Bunta Sugawara. Se trata del séptimo largometraje dirigido por Miyazaki dentro del estudio y de la decimosegunda producción de Ghibli. El filme cuenta la historia de una niña de diez años llamada Chihiro, quien durante una mudanza se ve atrapada en un mundo mágico y sobrenatural, teniendo como misión buscar su libertad y la de sus padres, y así poder regresar a su mundo.',9000,'2003-11-07',6,'El viaje de Chihiro'),(13,1,'https://filmscover.s3.us-east-2.amazonaws.com/133fa03b-a567-4b9f-82cf-e8834b06e8cc.jpg','es una película de anime ciberpunk post-apocalíptica dirigida por Katsuhiro Ōtomo estrenada el 16 de julio de 1988 en Japón.3​ Es una adaptación del manga homónimo4​ creado por el mismo Otomo y coescrita con ayuda de Izo Hashimoto.5​ El diseño de personajes y la ambientación de la película fueron adaptados desde el manga original,6​ mientras que la trama es una reestructuración de eventos que difiere de la versión impresa debido a que este todavía estaba en proceso de publicación.',5000,'1988-07-16',5,'Akira'),(14,1,'https://filmscover.s3.us-east-2.amazonaws.com/2802d1ed-f52a-4ed9-9333-5fe58cf94b06.jpg','Tras el lanzamiento de Mortal Kombat en abril de 2021, el productor Todd Garner, el escritor Greg Russo y el director Simon McQuoid comenzaron conversaciones sobre el futuro de la franquicia, incluidas películas independientes centradas en Johnny Cage y Bi-Han, y una secuela de la película de 2021. Algunos miembros del elenco y el equipo expresaron interés en regresar a una secuela y, en 2021, los medios de comunicación informaron que el estudio estaba planeando desarrollar otras entregas de la franquicia Mortal Kombat. Para 2022, Warner Bros. había dado luz verde a la secuela, con Slater listo para escribir el guion y McQuoid regresó para dirigir el proyecto. El rodaje comenzó en junio de 2023 en Australia, se detuvo a mediados de julio debido a la huelga SAG-AFTRA, se reanudó a mediados de noviembre y concluyó a finales de enero de 2024.',30000,'2024-10-24',25,'Mortal Kombat 2'),(15,0,'https://filmscover.s3.us-east-2.amazonaws.com/dc55a7ba-6436-41a9-8cd2-b37a00d85fcc.jpg','Los planes para una tercera película de Sonic the Hedgehog se anunciaron en febrero de 2022 durante el evento de inversores de ViacomCBS antes del estreno de Sonic 2, la película. Debido a la huelga SAG-AFTRA de 2023, el rodaje de los personajes animados comenzó en julio de 2023 en Surrey, Inglaterra, mientras que el rodaje con actores físicos comenzó en noviembre de ese año en Londres, concluyendo la producción en marzo de 2024. La película se inspira en los videojuegos Sonic Adventure 2 (2001) y Shadow the Hedgehog (2005). Reeves fue elegido debido a los paralelismos entre su interpretación en las películas de John Wick y la visión de Fowler para Shadow, mientras que muchos elementos del personaje en los juegos se adaptaron para la pantalla en una interpretación «fiel a los fanáticos».',25000,'2024-12-26',0,'Sonic 3'),(16,0,'https://filmscover.s3.us-east-2.amazonaws.com/88597c13-1290-4a89-99b0-7021b4e60e76.jpg','Bilbo Bolsón y el resto de la compañía de Thorin Escudo de Roble contemplan desde Erebor como Smaug destruye la Ciudad del Lago, en venganza por su intento de matarle en la película anterior. Bardo el Arquero logra escapar de la celda en la que estaba encerrado, recupera la Flecha Negra con la ayuda de su hijo Bain y la usa para matar a Smaug. El cuerpo de Smaug cae encima del bote del Gobernador, quien egoístamente pretendía huir de la ciudad junto con sus riquezas. Convertido en el nuevo líder de los supervivientes de la Ciudad del Lago, Bardo decide buscar refugio en las ruinas de Valle, mientras Legolas y Tauriel se disponen a viajar al Monte Gundabad para investigar acerca de las recientes acciones de los orcos. En Erebor, Thorin ha caído preso del \'mal del dragón\' al haber recuperado el reino y el tesoro, y está completamente obsesionado con encontrar la Piedra del Arca, que se encuentra en poder de Bilbo, pero el hobbit no se atreve a dársela por temor a que su \'enfermedad\' empeore. Al enterarse de que los supervivientes del ataque de Smaug han llegado a Valle, Thorin ordena sellar la entrada de Erebor.',30000,'2014-11-30',0,'El Hobbit: La batalla de los cinco ejércitos'),(53,0,'https://filmscover.s3.us-east-2.amazonaws.com/ef4da4e0-e289-4d0e-9bc1-5f25ff931bc6.jpg','La película está dirigida por Colin Trevorrow,4​ así como la sexta entrega de la franquicia Parque Jurásico y la tercera película de la serie Jurassic World, que comenzó en 2015.5​ El rodaje tuvo lugar de febrero a noviembre de 2020 en Vancouver, Canadá, Pinewood Studios de Inglaterra, Malta y Suiza. Legendary Pictures no participó en la producción de la película debido a la expiración de su asociación de cuatro años con Universal.',22000,'2022-06-01',0,'Jurassic World: Dominion'),(105,1,NULL,'Un exesclavo une fuerzas con un cazador de recompensas alemán que lo liberó y lo ayuda a buscar a los criminales más buscados del sur de los Estados Unidos, con la esperanza de reencontrarse con su esposa.',8000,'2013-02-01',8,'Django sin cadenas');
/*!40000 ALTER TABLE `film_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_entity_seq`
--

DROP TABLE IF EXISTS `film_entity_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_entity_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_entity_seq`
--

LOCK TABLES `film_entity_seq` WRITE;
/*!40000 ALTER TABLE `film_entity_seq` DISABLE KEYS */;
INSERT INTO `film_entity_seq` VALUES (201);
/*!40000 ALTER TABLE `film_entity_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirmation_status` int DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (3,1,36000,'stefanylugocp@gmail.com'),(4,0,12000,'stefanylugocp@gmail.com'),(5,0,0,'arman.2.r@gmail.com');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_item`
--

DROP TABLE IF EXISTS `purchase_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `ticket_price` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `film_id` int NOT NULL,
  `purchase_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8wgdosc5f1m1ifr2jdf66d77` (`film_id`),
  KEY `FK1mncc5yaore1sibgpj3jc4a7u` (`purchase_id`),
  CONSTRAINT `FK1mncc5yaore1sibgpj3jc4a7u` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `FKj8wgdosc5f1m1ifr2jdf66d77` FOREIGN KEY (`film_id`) REFERENCES `film_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_item`
--

LOCK TABLES `purchase_item` WRITE;
/*!40000 ALTER TABLE `purchase_item` DISABLE KEYS */;
INSERT INTO `purchase_item` VALUES (6,3,12000,36000,3,3),(7,1,12000,12000,3,4);
/*!40000 ALTER TABLE `purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `rol` enum('ADMIN','USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'arman.2.r@gmail.com','Armando','Rubio','$2a$10$iq9zgM6C3co5FA8cF5yO.eEACxihiRNgQ0bSR61ei2TxkTmbHorbG','3155443749','ADMIN'),(2,1,'stefanylugocp@gmail.com','stefany','Lugo','$2a$10$Bc93Nkcd63eV/0YPgZhNiO.XrmEN0FaXuVYDHJ2JLllFbfg0BWMwq','3112044337','USER'),(3,1,'ancizar.rubio@gmail.com','Ancizar','Rubio','$2a$10$6zGAtx7JxNSDLUL.35W7XuVVBLawvEQB64C1x0sw4dhsget6MQnpe','3143405890','USER'),(4,1,'andrelly2009@hotmail.com','andrelly','riaño','$2a$10$m692eo0/lzNbj6KBc6tZDOQoML6wp.Vl6oyqHd0hgsJMDK7pupPcq','3112044337','USER'),(5,1,'fabianpro@gmail.com','fabian','gomez','$2a$10$qLBYWiaP6UCOztCqeTNLvez4vLScblJZ5Ai.F586DJLEKnSYa5aD2','3154477878','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-25  0:16:28
