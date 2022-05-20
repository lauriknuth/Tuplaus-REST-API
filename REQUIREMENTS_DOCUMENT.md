Tehtävänanto:

Tehtävänäsi on toteuttaa yksinkertainen pelimoottori, jonka avulla voi pelata pokeripeleistä tuttua Tuplausta. Tuplauksessa pelaaja yrittää arvata, onko edessä oleva yksittäinen kortti pieni vai suuri. Pieni on numerot 1-6, suuri 8-13. Jos tulee 7, pelaaja häviää aina.

Pelimoottorilla tarkoitetaan tässä yhteydessä palvelinta, joka tarjoaa HTTP API:n peli-clienteille ja pyörittää pelilogiikkaa. Peli-clienttia ei tarvitse toteuttaa tässä harjoituksessa.

Voit valita käytettävän ohjelmointikielen ja tehdä muut tekniset ratkaisut itse.

***<> Tehtävä on toteutettu Javalla, Spring Bootilla ja Gradlella***

Tehtävät:

1. Suunnittele ja dokumentoi peli-clientin ja pelimoottorin välinen HTTP-rajapinta

***<> Rajapinnan kuvaus README.md:ssä***

Pelattaessa pelikierrosta peli-client välittää pelimoottorille pelaajan yksilöivän tunnisteen, panoksen ja pelaajan valinnan (pieni tai suuri). Vastauksessa pelimoottori välittää peli-clientille arvotun kortin, tiedon siitä voittiko pelikierros, mahdollisen voiton suuruuden ja pelaajan pelitilin jäljellä olevan saldon. Voittaessaan pelaaja voi jatkaa tuplausta tai kotiuttaa voitot.

2. Suunnittele ja toteuta tietokanta pelimoottorille

Pelaajista tallennettavat tiedot:
- Palaajan yksilöivä tunniste
- Nimi
- Pelitilin saldo

Pelitapahtumasta tallennettavat tiedot:
- Aikaleima
- Pelaajan yksilöivä tunniste
- Panos
- Pelaajan valinta (pieni vai suuri)
- Arvottu kortti
- Mahdollisen voiton suuruus

3. Toteuta pelimoottori

Käsitellessään pelikierrosta pelimoottori arpoo pelin lopputuloksen ja vähentää pelitilin saldosta pelaajan panostuksen. Jos pelaaja voittaa, pelaaja voi joko jatkaa tuplausta tai kotiuttaa voitot. Jos pelaaja kotiuttaa voitot, pelimoottori lisää ne pelitilin saldoon.

Jos pelitilin saldo ei riitä, järjestelmä palauttaa peli-clientille virheen.

4. Tee järjestelmälle testit

***<> Testit tallennettu "Testing.http"-tiedostoon "test"-kansiossa. Testit ajettu IntelliJ HTTP Client pluginilla mutta toimiii myös selaimella***

6. Kirjoita ohjeet pelimoottorin ja testien ajamiseen

***<> Ohjeet README.md:ssä***

7. Toimita valmis toteuksesi meille ja valmistaudu keskustelemaan tekemistäsi ratkaisuista